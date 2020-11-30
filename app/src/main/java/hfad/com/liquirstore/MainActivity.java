package hfad.com.liquirstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvContacts;
    ArrayList<LiquirBatch> LiquirBatchList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LiquirBatchList=new ArrayList<LiquirBatch>();
        LiquirBatchAdapter x= new LiquirBatchAdapter(LiquirBatchList);
        rvContacts = (RecyclerView)findViewById(R.id.rvContacts);
        JSONObject postData = new JSONObject();

        try {
            postData.put("email", "email");

            volleyPost(postData); ///calls the volley method to make request. Email, password values are passed to volleyPost() method

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    public void volleyPost(JSONObject postData){
        ConnectionString obj=new ConnectionString();

        String postUrl = obj.connectioncode+"manufacturer/getBottleBatch1";
        RequestQueue requestQueue = Volley.newRequestQueue(this);



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.i("MyActivity", "MyClass.getView() — get item number "+response);
                handleRecycler(response);// After the trespons e is recieved handleResponse() method will be called to read json response and take next steps.
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

    public void handleRecycler(JSONObject response)
    {
        try{
            JSONArray user_wallets = response.getJSONArray("result");
            JSONObject[] batchList=new JSONObject[user_wallets.length()];

            for(int i = 0; i<user_wallets.length();i++){

                JSONObject wallet = user_wallets.getJSONObject(i);
              //  batchList[i]=wallet;
               LiquirBatch batch =new LiquirBatch(wallet.getInt("bottle_batch_id"), wallet.getString("name") ,wallet.getString("description") ,wallet.getString("creator_permit") ,wallet.getString("creator_name") , wallet.getString("current_owner_permit") ,wallet.getString("current_owner_name") , wallet.getInt("volume"), wallet.getString("product_id") );
                LiquirBatchList.add(batch);
            }
            Log.i("333333333333333333333333333333", "e");
            Log.i("MyActivity", "Input suceesss ");
            LiquirBatchAdapter lb=new LiquirBatchAdapter(LiquirBatchList);
            lb.setOnItemClickListener(new LiquirBatchAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
//                    ToddyBatch a=toddyBatchList.get(position);
//                    String b=String.valueOf(a.getVolume());
//                    int batchID=a.getBatch_id();
//                    Bundle bundle=new Bundle();
                    LiquirBatch obb=LiquirBatchList.get(position);
                    String name=obb.getName();
                    String productId=obb.getProduct_id();
                    int batchId=obb.getBottle_batch_id();



                    // String name = users.get(position).name;
                    // Toast.makeText(getContext(),  b+"   was clicked!", Toast.LENGTH_SHORT).show();
                    changeScreen(batchId,productId,name);;
                }
            });
            rvContacts.setAdapter(lb);
            // Set layout manager to position the items
           rvContacts.setLayoutManager(new LinearLayoutManager(this));







        }
        catch(Exception e)
        {
            Log.i("errorr444444444444444444", "e");
            System.out.println(e);

        }


    }

    public void changeScreen(int batchId,String productID,String name){

        Intent intent=new Intent(this,SellBatch.class);
        intent.putExtra("batchId",batchId);
        intent.putExtra("productId",productID);
        intent.putExtra("name",name);
//                    intent.putExtra("batchID",batchID);
//                    intent.putExtra("volume",a.getVolume());
//                    intent.putExtra("date",a.getDate_created());
        startActivity(intent);
    }


}