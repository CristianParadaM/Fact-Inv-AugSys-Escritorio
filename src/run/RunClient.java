package run;

import controller.Controller;

public class RunClient {
	public static void main(String[] args) {
		Controller.getInstance().startApp("localhost", 12345);
	}
}
