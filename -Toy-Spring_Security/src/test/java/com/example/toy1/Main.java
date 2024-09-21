package com.example.toy1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws IOException {

		sample();
	}



	private static void sample() throws IOException {


		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();


		PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());


		int testCaseNumber = Integer.parseInt(br.readLine());

		for (int i = 0; i < testCaseNumber; i++) {
			int answer = 0;

			String[] list = br.readLine().split(" ");

			if (Integer.parseInt(list[0])  == 1){
				sb.append(list[0]);
					continue;
			}

			String[] numberList = br.readLine().split(" ");


			for (String s : numberList) {
				queue.add(Integer.parseInt(s));
			}

				for (int j = 0; j < numberList.length; j++) {


				if(Integer.parseInt(numberList[j]) != queue.peek()){
				queue.poll();
				answer++;
				}


				if(numberList[j] == numberList[Integer.parseInt(list[1])]){
					answer++;
					sb.append(answer);
					break;
				}

				}


			queue.clear();

		}

		System.out.println(sb);


	};

}
