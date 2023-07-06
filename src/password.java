import java.util.Base64;
import java.util.Base64.Decoder;

public class password {

	public static String Youo(String xuri) {

		Object decoder;
		Decoder xgdv = Base64.getUrlDecoder();
		String bhii = new String(xgdv.decode(xuri));
		String dStr = new String(bhii);

		return dStr;
	}

}