package lesson15;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // Показывает, где эта аннотация будет использоваться:
// source - нужна только на этапе компиляции,
// class - информация будет записана в файл класса, но для рефлексии будет недоступна,
// runtime - эта аннотация будет видна для рефлексии.
@Target(ElementType.METHOD) // Данная аннотация показывает, к чему она будет применяться.
public @interface SimpleAnnotation {
}
