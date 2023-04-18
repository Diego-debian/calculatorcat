package unal.todosalau.calculatorcat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
public class MainActivity extends AppCompatActivity {

    private EditText editTextOperand1;
    private EditText editTextOperand2;
    private TextView textViewResult;
    private ImageView imageViewCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtiene los componentes de la interfaz de usuario
        editTextOperand1 = findViewById(R.id.editTextOperand1);
        editTextOperand2 = findViewById(R.id.editTextOperand2);
        textViewResult = findViewById(R.id.textViewResult);
        imageViewCat = findViewById(R.id.imageViewCat);

        // Define los listeners para los botones de operación
        Button buttonAdd = findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("+");
            }
        });

        Button buttonSubtract = findViewById(R.id.buttonSubtract);
        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("-");
            }
        });

        Button buttonMultiply = findViewById(R.id.buttonMultiply);
        buttonMultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("*");
            }
        });

        Button buttonDivide = findViewById(R.id.buttonDivide);
        buttonDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate("/");
            }
        });
    }

    private void calculate(String operator) {
        // Obtiene los operandos
        String operand1String = editTextOperand1.getText().toString();
        String operand2String = editTextOperand2.getText().toString();

        // Valida que los operandos no estén vacíos
        if (operand1String.isEmpty() || operand2String.isEmpty()) {
            Toast.makeText(this, "Ingrese ambos operandos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convierte los operandos a números
        double operand1 = Double.parseDouble(operand1String);
        double operand2 = Double.parseDouble(operand2String);

        // Realiza la operación utilizando la clase Calculator
        double result = 0;
        Calculator calculator = new Calculator();

        switch (operator) {
            case "+":
                result = calculator.add(operand1, operand2);
                break;
            case "-":
                result = calculator.subtract(operand1, operand2);
                break;
            case "*":
                result = calculator.multiply(operand1, operand2);
                break;
            case "/":
                // Valida que el segundo operando no sea cero
                if (operand2 == 0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                result = calculator.divide(operand1, operand2);
                break;
        }

        // Muestra el resultado
        textViewResult.setText(String.format("%.2f", result));

        // Selecciona la imagen de gato correspondiente
        if (result >= 0) {
            imageViewCat.setImageResource(R.drawable.cat1);
        } else {
            imageViewCat.setImageResource(R.drawable.cat_sad);
        }
    }
}