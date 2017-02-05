package model;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yufei
 */
public class ShowButtonDemo {

	public static void libo() {
		System.out.println("!!!!!!!! 2017年2月2日21:22:58");
	}

	public static void main(String[] args) {
		ButtonDemo gui = new ButtonDemo();
		gui.setVisible(true);

		ButtonDemo g = new ButtonDemo();
		gui.setVisible(true);
		libo();
		libo();
	}

	public void feiyu(int i) {
		System.out.println("20170202 21:22");

	}
}