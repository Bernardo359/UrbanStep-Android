package com.example.urbanstep.services;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.urbanstep.utils.VolleySingleton;

import java.util.Map;

public class ApiService {

    private static final String BASE_URL = "http://10.0.2.2/urbanstep/backend/web/api/"; // URL base para desenvolvimento
    private Context context;

    public ApiService(Context context) {
        this.context = context;
    }

    // Método para construir URLs completas
    private String getFullUrl(String endpoint) {
        return BASE_URL + endpoint;
    }

    // Método genérico para enviar requisições POST
    public void postRequest(String endpoint, Map<String, String> params, final ApiCallback callback) {
        String url = getFullUrl(endpoint);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }
        };
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Método genérico para requisições GET
    public void getRequest(String endpoint, final ApiCallback callback) {
        String url = getFullUrl(endpoint);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String errorMessage;
                        if (error.getMessage() != null) {
                            errorMessage = error.getMessage();
                        } else if (error.networkResponse != null) {
                            errorMessage = "Código de resposta: " + error.networkResponse.statusCode;
                        } else {
                            errorMessage = "Erro desconhecido ocorreu.";
                        }

                        callback.onError(errorMessage);
                    }
                });
        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    // Interface de callback para sucesso ou erro
    public interface ApiCallback {
        void onSuccess(String response);
        void onError(String errorMessage);
    }
}
