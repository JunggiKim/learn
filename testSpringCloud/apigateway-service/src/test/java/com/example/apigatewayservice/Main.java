package com.example.apigatewayservice;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException {
		solution();
	}

	public static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		List<Integer> numberList = new ArrayList<>();

		int loopCount = Integer.parseInt(br.readLine());

		String[] orderList = new String[loopCount];


		for (int i = 0; i < loopCount; i++) {
			orderList[i] = br.readLine();
		}


		for (int i = 0; i < orderList.length; i++) {

			if(orderList[i].contains("push")){
				numberList.add(Integer.parseInt(orderList[i].split(" ")[1]));
			}

			if(orderList[i].equals("pop")){

				if(numberList.isEmpty()){
					System.out.println("-1");
					continue;
				}
				System.out.println(numberList.get(0));
				numberList.remove(0);
			}

			if(orderList[i].equals("size")){
				System.out.println(numberList.size());
			}

			if(orderList[i].equals("empty")){
				int emptyNumber = numberList.isEmpty() ? 1 : 0;
				System.out.println(emptyNumber);
			}

			if(orderList[i].equals("front")){

				if(numberList.isEmpty()){
					System.out.println("-1");
					continue;
				}

				System.out.println(numberList.get(0));
			}

			if(orderList[i].equals("back")){

				if(numberList.isEmpty()){
					System.out.println("-1");
					continue;
				}

				System.out.println(numberList.get(numberList.size() - 1));
			}
		}

	}

}
