package andrew.sp.alura.PrimeiraAPI.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandle {

	@Autowired
	private MessageSource messageSource;

	// ele altera o status code
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	// você tem que apontar qual erro esse método vai
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErroFormDto> handleMethodValidation(MethodArgumentNotValidException exception) {
		// eu quero que ele devolva uma lista de erros que foram cometidos
		List<ErroFormDto> listaErro = new ArrayList<>();
		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErroFormDto erroDto = new ErroFormDto(e.getField(), mensagem);

			listaErro.add(erroDto);
		});
		return listaErro;
	}
}
