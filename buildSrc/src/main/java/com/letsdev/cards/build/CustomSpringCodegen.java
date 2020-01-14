package com.letsdev.cards.build;

import io.swagger.codegen.CodegenProperty;
import io.swagger.codegen.languages.SpringCodegen;
import io.swagger.models.properties.*;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Enables Java 8 date-time library without enabling default methods in interfaces.
 * Also adds support for custom property formats.
 *
 * @see CustomCodegenProperty
 */
public class CustomSpringCodegen extends SpringCodegen {

    private static final String COMMA = ",";
    private static final String X_FORMAT = "x-format";

    @Override
    public String findCommonPrefixOfVars(final List<Object> vars) {
        return "";
    }

    @Override
    public void processOpts() {
        super.processOpts();
        useJava8DateLibrary();
    }

    @Override
    public CustomCodegenProperty fromProperty(final String name, final Property p) {
        var originalCodegenProperty = super.fromProperty(name, p);
        var customCodegenProperty = clone(originalCodegenProperty);
        return applyCustomFormats(customCodegenProperty, p);
    }

    private void useJava8DateLibrary() {
        additionalProperties.put("jsr310", "true");
        typeMapping.put("date", "LocalDate");
        importMapping.put("LocalDate", "java.time.LocalDate");
        typeMapping.put("DateTime", "OffsetDateTime");
        importMapping.put("OffsetDateTime", "java.time.OffsetDateTime");
    }

    private CustomCodegenProperty clone(final CodegenProperty originalCodegenProperty) {
        var customCodegenProperty = new CustomCodegenProperty();
        copyProperties(customCodegenProperty, originalCodegenProperty);
        return customCodegenProperty;
    }

    private void copyProperties(final Object to, final Object from) {
        var fromFields = FieldUtils.getAllFields(from.getClass());
        for (var fromField : fromFields) {
            try {
                var fromValue = FieldUtils.readField(fromField, from);
                var toField = FieldUtils.getField(to.getClass(), fromField.getName());
                FieldUtils.writeField(toField, to, fromValue);
            } catch (ReflectiveOperationException ignore) {
            }
        }
    }

    private CustomCodegenProperty applyCustomFormats(final CustomCodegenProperty customCodegenProperty,
                                                     final Property p) {
        var formats = new HashSet<String>();
        if (customCodegenProperty.dataFormat != null && !customCodegenProperty.dataFormat.isBlank()) {
            formats.addAll(csvToList(customCodegenProperty.dataFormat));
        }
        if (customCodegenProperty.vendorExtensions != null && customCodegenProperty.vendorExtensions.containsKey(X_FORMAT)) {
            var dataFormat = (String) customCodegenProperty.vendorExtensions.get(X_FORMAT);
            formats.addAll(csvToList(dataFormat));
        }
        for (var format : formats) {
            switch (format) {
                case "credit-card-number":
                case "creditCardNumber":
                    if (isStringProperty(p))
                        customCodegenProperty.creditCardNumber = true;
                    break;
                case "currency":
                    if (isNumericProperty(p))
                        customCodegenProperty.currency = true;
                    break;
                case "email":
                    if (isStringProperty(p))
                        customCodegenProperty.email = true;
                    break;
                case "future":
                    if (isDateProperty(p))
                        customCodegenProperty.future = true;
                    break;
                case "not-blank":
                case "notBlank":
                    if (isStringProperty(p))
                        customCodegenProperty.notBlank = true;
                    break;
                case "phone":
                    if (isStringProperty(p))
                        customCodegenProperty.phone = true;
                    break;
                case "password":
                    if (isStringProperty(p))
                        customCodegenProperty.password = true;
                    break;
                case "unique-elements":
                case "uniqueElements":
                    if (isArrayProperty(p))
                        customCodegenProperty.uniqueElements = true;
                    break;
                case "url":
                    if (isStringProperty(p))
                        customCodegenProperty.url = true;
                    break;
            }
        }
        return customCodegenProperty;
    }

    private List<String> csvToList(final String dataFormat) {
        return Arrays.asList(dataFormat.split(COMMA));
    }

    private boolean isArrayProperty(final Property p) {
        return p instanceof ArrayProperty;
    }

    private boolean isDateProperty(final Property p) {
        return p instanceof DateProperty || p instanceof DateTimeProperty;
    }

    private boolean isNumericProperty(final Property p) {
        return p instanceof AbstractNumericProperty;
    }

    private boolean isStringProperty(final Property p) {
        return "string".equals(p.getType());
    }

}
