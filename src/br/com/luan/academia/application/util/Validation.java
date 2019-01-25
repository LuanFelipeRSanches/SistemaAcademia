package br.com.luan.academia.application.util;

import javax.ejb.ApplicationException;

//Metado para ver se o usuario preenche
@ApplicationException
public class Validation {
	public static void assertNotEmpty(Object obj) {
		if (obj instanceof String) {
			String s = (String) obj;
			if (StringUtils.isEmpty(s)) {
				try {
					throw new ValidationException("Texto não pode ser nulo");
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}
		if (obj == null) {
			try {
				throw new ValidationException("Valor não pode ser nulo");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
