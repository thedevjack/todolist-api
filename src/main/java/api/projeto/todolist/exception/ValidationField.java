package api.projeto.todolist.exception;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
@Builder
@AllArgsConstructor
public class ValidationField {

   private String filed;
   private String message;

   public ValidationField(FieldError fieldError) {
	  filed = fieldError.getField();
	  message = fieldError.getDefaultMessage();
   }

   public String toString() {
	  return filed + " " + message;
   }
}
