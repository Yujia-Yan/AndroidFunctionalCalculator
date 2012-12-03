package org.nightcode.calculator;
import org.nightcode.expressionParser.*;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CalculatorInstanceActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(findViewById(R.id.editText1)
				.getWindowToken(), 0);
		EditText edit = (EditText) findViewById(R.id.editText1);
		edit.setInputType(InputType.TYPE_NULL);
		EditText edit2 = (EditText) findViewById(R.id.editText2);
		edit2.setFocusable(false);
		edit2.setInputType(InputType.TYPE_NULL);
		// imm.hideSoftInputFromWindow(findViewById(R.id.editText2).getWindowToken(),
		// 0);
		OnClickListener ls = new calculatorListener();
		findViewById(R.id.btn0).setOnClickListener(ls);
		findViewById(R.id.btn1).setOnClickListener(ls);
		findViewById(R.id.btn2).setOnClickListener(ls);
		findViewById(R.id.btn3).setOnClickListener(ls);
		findViewById(R.id.btn4).setOnClickListener(ls);
		findViewById(R.id.btn5).setOnClickListener(ls);
		findViewById(R.id.btn6).setOnClickListener(ls);
		findViewById(R.id.btn7).setOnClickListener(ls);
		findViewById(R.id.btn8).setOnClickListener(ls);
		findViewById(R.id.btn9).setOnClickListener(ls);
		findViewById(R.id.btnAdd).setOnClickListener(ls);
		findViewById(R.id.btnAns).setOnClickListener(ls);
		findViewById(R.id.btnBack).setOnClickListener(ls);
		findViewById(R.id.btnC).setOnClickListener(ls);
		findViewById(R.id.btnCe).setOnClickListener(ls);
		findViewById(R.id.btnComma).setOnClickListener(ls);
		findViewById(R.id.btnCos).setOnClickListener(ls);
		findViewById(R.id.btnCosh).setOnClickListener(ls);
		findViewById(R.id.btnDiv).setOnClickListener(ls);
		findViewById(R.id.btnDot).setOnClickListener(ls);
		findViewById(R.id.btnE).setOnClickListener(ls);
		findViewById(R.id.btnEnter).setOnClickListener(ls);
		findViewById(R.id.btnExclaim).setOnClickListener(ls);
		findViewById(R.id.btnExp).setOnClickListener(ls);
		findViewById(R.id.btnLog).setOnClickListener(ls);
		findViewById(R.id.btnLp).setOnClickListener(ls);
		findViewById(R.id.btnMin).setOnClickListener(ls);
		findViewById(R.id.btnMul).setOnClickListener(ls);
		findViewById(R.id.btnPi).setOnClickListener(ls);
		findViewById(R.id.btnPow).setOnClickListener(ls);
		findViewById(R.id.btnRp).setOnClickListener(ls);
		findViewById(R.id.btnSetX1).setOnClickListener(ls);
		findViewById(R.id.btnSetX2).setOnClickListener(ls);
		findViewById(R.id.btnSetX3).setOnClickListener(ls);
		findViewById(R.id.btnSin).setOnClickListener(ls);
		findViewById(R.id.btnSinh).setOnClickListener(ls);
		findViewById(R.id.btnSqrt).setOnClickListener(ls);
		findViewById(R.id.btnTan).setOnClickListener(ls);
		findViewById(R.id.btnTanh).setOnClickListener(ls);
		findViewById(R.id.btnX1).setOnClickListener(ls);
		findViewById(R.id.btnX2).setOnClickListener(ls);
		findViewById(R.id.btnX3).setOnClickListener(ls);
		if(
		!ExpressionParser.VariableTable.contains("ans")){
			ExpressionParser.VariableTable.put("ans", 0d);
		}
		if(
				!ExpressionParser.VariableTable.contains("x1")){
					ExpressionParser.VariableTable.put("x1", 0d);
				}
		if(
				!ExpressionParser.VariableTable.contains("x2")){
					ExpressionParser.VariableTable.put("x2", 0d);
				}
		if(
				!ExpressionParser.VariableTable.contains("x3")){
					ExpressionParser.VariableTable.put("x3", 0d);
				}
	}
	void evalAns(){
		EditText edit=(EditText)findViewById(R.id.editText1);
		EditText edit2=(EditText)findViewById(R.id.editText2);
		ExpressionParser parser=new ExpressionParser(edit.getText().toString().toCharArray());
		if(ExpressionParser.VariableTable.contains("ans")){
					ExpressionParser.VariableTable.remove("ans");
		}
					try {
						double result=parser.parse();
						ExpressionParser.VariableTable.put("ans", result);
						edit2.setText(Double.toString(result));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						edit2.setText(e.getMessage());
						e.printStackTrace();
					}
				
	}
	public class calculatorListener implements OnClickListener {
		
		@Override
		
		public void onClick(View v) {
			EditText edit=(EditText)findViewById(R.id.editText1);
			EditText edit2=(EditText)findViewById(R.id.editText2);
			Button button=(Button)v;
			switch(v.getId()){
			case R.id.btnEnter:evalAns();break;
			case R.id.btnBack:
				int start=edit.getSelectionStart();
				int end=edit.getSelectionEnd();
				if(start==end&&start>0)
					start--;
				edit.getText().replace(start,end, "");
				break;
			case R.id.btnC:
				edit2.getText().clear();break;
			case R.id.btnCe:
				edit.getText().clear();edit2.getText().clear();break;
			case R.id.btnSetX1:
				if(ExpressionParser.VariableTable.contains("x1")){
				ExpressionParser.VariableTable.remove("x1");
			}
				ExpressionParser.VariableTable.put("x1", ExpressionParser.VariableTable.get("ans"));
				break;
			case R.id.btnSetX2:
				if(ExpressionParser.VariableTable.contains("x2")){
					ExpressionParser.VariableTable.remove("x2");
				}
					ExpressionParser.VariableTable.put("x2", ExpressionParser.VariableTable.get("ans"));
				break;
			case R.id.btnSetX3:
				if(ExpressionParser.VariableTable.contains("x3")){
					ExpressionParser.VariableTable.remove("x3");
				}
					ExpressionParser.VariableTable.put("x3", ExpressionParser.VariableTable.get("ans"));
				break;
			default:  {
				
				edit.getText().insert(edit.getSelectionStart(), button.getText());
			}
			}
		}

	}
}