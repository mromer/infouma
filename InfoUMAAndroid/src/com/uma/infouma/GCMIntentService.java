package com.uma.infouma;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	public GCMIntentService() {
		super("964522623936");
	}

	@Override
	protected void onError(Context context, String errorId) {
		Log.d("GCMTest", "REGISTRATION: Error -> " + errorId);
	}

	@Override
	protected void onMessage(Context context, Intent intent) {
		String msg = intent.getExtras().getString("msg");
		Log.d("GCMTest", "Mensaje: " + msg);
		mostrarNotificacion(context, msg);
	}

	@Override
	protected void onRegistered(Context context, String regId) {
    	Log.d("GCMTest", "REGISTRATION: Registrado OK.");
    	
		
		Log.d("GCMTest", "REGISTRATION: regId:" + regId);    	
	}

	@Override
	protected void onUnregistered(Context context, String regId) {
		Log.d("GCMTest", "REGISTRATION: Desregistrado OK.");
	}

	
	private void mostrarNotificacion(Context context, String msg)
	{
		//Obtenemos una referencia al servicio de notificaciones
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager notManager =
		    (NotificationManager) context.getSystemService(ns);
		
		//Configuramos la notificaci�n
		int icono = android.R.drawable.stat_sys_warning;
		CharSequence textoEstado = "Alerta!";
		long hora = System.currentTimeMillis();
		 
		Notification notif =
		    new Notification(icono, textoEstado, hora);
		
		//Configuramos el Intent
		Context contexto = context.getApplicationContext();
		CharSequence titulo = "Nuevo Mensaje";
		CharSequence descripcion = msg;
		 
		Intent notIntent = new Intent(contexto,
		    GCMIntentService.class);
		 
		PendingIntent contIntent = PendingIntent.getActivity(
		    contexto, 0, notIntent, 0);
		 
		notif.setLatestEventInfo(
		    contexto, titulo, descripcion, contIntent);
		
		//AutoCancel: cuando se pulsa la notificai�n �sta desaparece
		notif.flags |= Notification.FLAG_AUTO_CANCEL;
		
		//Enviar notificaci�n
		notManager.notify(1, notif);
	}
}
