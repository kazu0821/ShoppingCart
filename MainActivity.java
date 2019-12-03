package com.example.shoppingcartnew;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{


    /* declaring the title
    * and how much it will cost every product.
    * And show up the picture*/

    Button btn;
    EditText txt1;

    ListView list;

    String[] maintitle ={
            "Peach",
            "Tomato",
            "Squash",
            "Orange",
            "Apple",

    };

    String[] subtitle ={
            "$2.34",
            "$1.29",
            "$1.15",
            "$1.49",
            "$1.17"

    };

    Integer[] imgid={
            R.drawable.peach,
            R.drawable.tomato,
            R.drawable.squash,
            R.drawable.orange,
            R.drawable.apple,


    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter=new MyListAdapter(this, maintitle, subtitle,imgid);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);

        btn=(Button)findViewById(R.id.button);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                if(position == 0)

                /* This if else statement will judge the position place. if this position number were equal,
                  These condition statement will implement*/
                {
                    //code specific to first list item
                    Toast.makeText(getApplicationContext(),"Place Your First Option Code",Toast.LENGTH_SHORT).show();
                }

                else if(position == 1)
                {
                    //code specific to 2nd list item
                    Toast.makeText(getApplicationContext(),"Place Your Second Option Code",Toast.LENGTH_SHORT).show();
                }

                else if(position == 2)
                {

                    //code specific to 3rd list item
                    Toast.makeText(getApplicationContext(),"Place Your Third Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 3)
                {

                    //code specific to 4th list item
                    Toast.makeText(getApplicationContext(),"Place Your Forth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 4)
                {

                    //code specific to 5thlist item
                    Toast.makeText(getApplicationContext(),"Place Your Fifth Option Code",Toast.LENGTH_SHORT).show();
                }
                else if(position == 5)
                {

                    //code specific to 6th list item
                    Toast.makeText(getApplicationContext(),"Place Your Sixth Option Code",Toast.LENGTH_SHORT).show();
                }





            }
        });

        btn.setOnClickListener(new View.OnClickListener() {


            /*  This parameter is responding on clickonLisner.
            * When user clicked on place order button, this for loop  will cout hiw many value user wants to buy.
            * This result will go to startActivity
            */
            @Override
            public void onClick(View v) {
                Intent it=new Intent(getBaseContext(),DetailActivity.class);

                int val[]=new int[5];

                for(int i=0;i<list.getCount();i++)
                {
                    View view=list.getChildAt(i);
                    txt1=view.findViewById(R.id.quantity);

                    int a=Integer.parseInt(String.valueOf(txt1.getText()));
                    val[i]=a;
                }

                it.putExtra("values",val);
                startActivity(it);


            }
        });


    }
}
