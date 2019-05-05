package com.quikr.assessment.ESearch.model;

public class TestModel {
	@Override
	public String toString() {
		return "TestModel [a=" + a + ", b=" + b + "]";
	}
	public int getA() {
		return a;
	}
	public void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	public void setB(int b) {
		this.b = b;
	}
	int a,b;
	public TestModel(int a, int b) {
		this.a=a;
		this.b=b;
	}
}

