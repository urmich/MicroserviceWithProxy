package microservicewithproxy.model.external;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {

    public boolean supports(Class clazz) {
        return Book.class.equals(clazz);
    }

    public void validate(Object obj, Errors e) {

        ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
        ValidationUtils.rejectIfEmpty(e, "isbn", "isbn.empty");
        ValidationUtils.rejectIfEmpty(e, "author", "author.empty");
        ValidationUtils.rejectIfEmpty(e, "pages", "pages.empty");

        Book book = (Book) obj;
        if (book.getPages() == null || book.getPages() <= 0) {
            e.rejectValue("pages", "positivevalue");
        }
    }
}