package com.letsdev.cards.build;

import io.swagger.codegen.CodegenProperty;

/**
 * Adds support for the following formats:
 *
 * <ul>
 * <li>creditCardNumber - <code>org.hibernate.validator.constraints.CreditCardNumber</code>;</li>
 * <li>currency - <code>javax.validation.constraints.Digits(integer = 12, fraction = 2)</code>;</li>
 * <li>email - <code>javax.validation.constraints.Email</code>;</li>
 * <li>future - <code>javax.validation.constraints.Future</code>;</li>
 * <li>notBlank - <code>javax.validation.constraints.NotBlank</code>;</li>
 * <li>uniqueElements - <code>org.hibernate.validator.constraints.UniqueElements</code>;</li>
 * <li>url - <code>org.hibernate.validator.constraints.URL</code>.</li>
 * </ul>
 *
 * @see CustomSpringCodegen
 */
public class CustomCodegenProperty extends CodegenProperty {

    public boolean creditCardNumber;
    public boolean currency;
    public boolean email;
    public boolean future;
    public boolean notBlank;
    public boolean password;
    public boolean phone;
    public boolean uniqueElements;
    public boolean url;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        var that = (CustomCodegenProperty) o;

        if (creditCardNumber != that.creditCardNumber) return false;
        if (currency != that.currency) return false;
        if (email != that.email) return false;
        if (future != that.future) return false;
        if (notBlank != that.notBlank) return false;
        if (password != that.password) return false;
        if (phone != that.phone) return false;
        if (uniqueElements != that.uniqueElements) return false;
        return url == that.url;
    }

    @Override
    public int hashCode() {
        var result = super.hashCode();
        result = 31 * result + (creditCardNumber ? 1 : 0);
        result = 31 * result + (currency ? 1 : 0);
        result = 31 * result + (email ? 1 : 0);
        result = 31 * result + (future ? 1 : 0);
        result = 31 * result + (notBlank ? 1 : 0);
        result = 31 * result + (password ? 1 : 0);
        result = 31 * result + (phone ? 1 : 0);
        result = 31 * result + (uniqueElements ? 1 : 0);
        result = 31 * result + (url ? 1 : 0);
        return result;
    }

}
