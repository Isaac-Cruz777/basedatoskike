package com.example.pracitcaarreglosbd;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    EditText txtClave, txtNombre, txtDepartamento, txtSueldo;
    Button btnCargar, btnAgregar, btnConsultar;
    Button btnActualizar, btnEliminar;
    ListView listaEmpleados;

    Basesita basesita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        basesita = new Basesita(this);

        txtClave = findViewById(R.id.txtClave);
        txtNombre = findViewById(R.id.txtNombre);
        txtDepartamento = findViewById(R.id.txtDepartamento);
        txtSueldo = findViewById(R.id.txtSueldo);

        btnCargar = findViewById(R.id.btnCargar);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        listaEmpleados = findViewById(R.id.listaEmpleados);

        btnCargar.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
        btnConsultar.setOnClickListener(this);
        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.btnCargar) {

            cargarEmpleados();

        } else if (v.getId() == R.id.btnConsultar) {

            mostrar();

        } else if (v.getId() == R.id.btnAgregar) {

            agregarEmpleado();

        }
        else if (v.getId() == R.id.btnActualizar) {

            actualizarEmpleado();

        }
        else if (v.getId() == R.id.btnEliminar) {

            eliminarEmpleado();

        }

    }




    public void cargarEmpleados() {

        String[] nombres = {"Carlos", "Pedro", "Luis", "Ana"};

        String[] departamentos = {"Sistemas", "Ventas", "Recursos Humanos", "Sistemas"};

        int[] sueldos = {15000, 18000, 20000, 17500};

        for (int i = 0; i < nombres.length; i++) {

            basesita.agregar(nombres[i], departamentos[i], sueldos[i]
            );
        }
    }

    public void mostrar() {

        Cursor cursor = basesita.consultar();

        ArrayList<String> empleados = new ArrayList<>();

        while (cursor.moveToNext()) {

            int clave = cursor.getInt(0);
            String nombre = cursor.getString(1);
            String departamento = cursor.getString(2);
            int sueldo = cursor.getInt(3);

            empleados.add(clave + " - " + nombre + " - " + departamento + " - $" + sueldo
            );
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                empleados
        );

        listaEmpleados.setAdapter(adaptador);

        cursor.close();
    }

    public void agregarEmpleado() {

        String nombre = txtNombre.getText().toString();
        String departamento = txtDepartamento.getText().toString();
        if (txtNombre.getText().toString().isEmpty() ||
                txtDepartamento.getText().toString().isEmpty() ||
                txtSueldo.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Completa todos los campos",
                    Toast.LENGTH_SHORT).show();

            return;
        }
        int sueldo = Integer.parseInt(
                txtSueldo.getText().toString()
        );

        basesita.agregar(nombre, departamento, sueldo);

        Toast.makeText(this,
                "Empleado agregado correctamente",
                Toast.LENGTH_SHORT).show();

        txtNombre.setText("");
        txtDepartamento.setText("");
        txtSueldo.setText("");

        mostrar();
    }

    public void actualizarEmpleado() {
        if (txtClave.getText().toString().isEmpty() ||
                txtSueldo.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Escribe la clave y el sueldo",
                    Toast.LENGTH_SHORT).show();

            return;
        }
        int clave = Integer.parseInt(
                txtClave.getText().toString()
        );

        int sueldo = Integer.parseInt(
                txtSueldo.getText().toString()
        );

        basesita.actualizar(clave, sueldo);

        Toast.makeText(this,
                "Empleado actualizado",
                Toast.LENGTH_SHORT).show();

        txtClave.setText("");
        txtSueldo.setText("");

        mostrar();
    }

    public void eliminarEmpleado() {
        if (txtClave.getText().toString().isEmpty()) {

            Toast.makeText(this,
                    "Escribe la clave del empleado",
                    Toast.LENGTH_SHORT).show();

            return;
        }
        int clave = Integer.parseInt(
                txtClave.getText().toString()
        );

        basesita.eliminar(clave);

        Toast.makeText(this,
                "Empleado eliminado",
                Toast.LENGTH_SHORT).show();

        txtClave.setText("");

        mostrar();
    }


}
