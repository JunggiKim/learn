package com.example.apigatewayservice;

public class programmers {

	public static void main(String[] args) {
		solution("...!@BaT#*..y.abcdefghijklm");
	}

	public static String solution(String new_id) {
		String answer = "";

		String replaceString = new_id
			.toLowerCase()
			.replaceAll("[^a-zA-Z0-9-_\\.]", "") //특수문자들 제거
			.replaceAll("\\.{2,}", ".") // .. 점 두개이상시 하나로 변경
			.replaceAll("^\\.|\\.$", ""); // . 점 맨앞과 맨뒤에 있는 것 제거

		String emptyCheckString = "".equals(replaceString) ? "a" : replaceString;

		String lengthCheckString =
			emptyCheckString.length() >= 16 ? emptyCheckString.substring(0, 15) : emptyCheckString;

		boolean lengthCheck = lengthCheckString.length() <= 2 ? true : false;

		while (lengthCheck) {

			lengthCheckString += lengthCheckString.substring(lengthCheckString.length() - 1);

			if (lengthCheckString.length() > 2)
				break;
		}

		answer = lengthCheckString.replaceAll("^\\.|\\.$", ""); // 마지막으로 . 점 맨앞과 맨뒤에 있는 것 제거

		return answer;
	}

}
