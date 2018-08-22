package io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

class D implements Externalizable {
	public int n1;
	public int n2;
	public int n3;
	public D(){}
	public D(int n1, int n2, int n3) {		
		this.n1 = n1;
		this.n2 = n2;
		this.n3 = n3;
	}
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}
}
public class T6 {

}
