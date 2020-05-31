package com.pma.app.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Elimane on May, 2020, at 12:32
 */

//https://docs.oracle.com/javase/tutorial/java/generics/wildcards.html <- Info about Class<?> generics
/*
*@Class is a parameterizable class, hence you can use the syntax Class<T> where T is a type.
* By writing Class<?>, you're declaring a Class object which can be of any type (? is a wildcard).
* The Class type is a type that contains meta-information about a class.
* */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)//Describe if the custom annotation should be in the byte code during runtime
@Constraint(validatedBy = UniqueValidator.class)
public @interface UniqueValue {

   String message() default "Unique constraint violated";

   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};

}
