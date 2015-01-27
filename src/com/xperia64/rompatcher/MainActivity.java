/*******************************************************************************
 * This file is part of ROM Patcher.
 * 
 * Copyright (c) 2014 xperia64.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 * 	Paul Kratt - main MultiPatch application for Mac OSX
 * 	byuu - UPS and BPS patchers
 * 	Neil Corlett - IPS patcher
 * 	Daniel Ekstr'm - PPF patcher
 * 	Josh MacDonald - XDelta
 * 	Colin Percival - BSDiff
 * 	xperia64 - port to Android and IPS32 support
 ******************************************************************************/
package com.xperia64.rompatcher;




import com.google.common.io.Files;
import com.xperia64.rompatcher.R;
import com.xperia64.rompatcher.javapatchers.APSGBAPatcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;
import java.util.Locale;

import android.text.InputFilter;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
//import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
//import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.text.Spanned;

public class MainActivity extends Activity {


	public static native int apsN64PatchRom(String romPath, String patchPath); 
	public static native int ipsPatchRom(String romPath, String patchPath); 
	public static native int ips32PatchRom(String romPath, String patchPath); 
	public static native int upsPatchRom(String romPath, String patchPath, String outputFile, int jignoreChecksum);
	public static native int xdelta1PatchRom(String romPath, String patchPath, String outputFile);
	public static native int ecmPatchRom(String romPath, String outFile, int bkup);
	public static native int dpsPatchRom(String romPath, String patchPath, String outFile);
	public static native int xdelta3PatchRom(String romPath, String patchPath, String outputFile);
	public static native int bsdiffPatchRom(String romPath, String patchPath, String outputFile);
	public static native int bpsPatchRom(String romPath, String patchPath, String outputFile, int jignoreChecksum);
	public static native int ppfPatchRom(String romPath, String patchPath, int jignoreChecksum);
	public static native int asmPatchRom(String romPath, String patchPath);
	public static native int dldiPatchRom(String romPath, String patchPath);
	public static native int xpcPatchRom(String romPatch, String patchPath, String outputFile);
	Context staticThis;
	CheckBox c;// = (CheckBox) findViewById(R.id.backupCheckbox);
	CheckBox d;// = (CheckBox) findViewById(R.id.altNameCheckbox);
	CheckBox r;// = (CheckBox) findViewById(R.id.ignoreCRC);
	CheckBox e;// = (CheckBox) findViewById(R.id.fileExtCheckbox);
	EditText ed;// = (EditText) findViewById(R.id.txtOutFile);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		staticThis=MainActivity.this;
		// Load native libraries
		try {
			System.loadLibrary("apsn64patcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab apspatcher!");
		}
		try {
			System.loadLibrary("ipspatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ipspatcher!");
		}
		try {
			System.loadLibrary("ipspatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ipspatcher!");
		}
		try {
			System.loadLibrary("upspatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab upspatcher!");
		}
		try {
			System.loadLibrary("xdelta3patcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab xdelta3patcher!");
		}
		try {
			System.loadLibrary("bpspatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab bpspatcher!");
		}
		try {
			System.loadLibrary("bzip2");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab bzip2!");
		}
		try {
			System.loadLibrary("bsdiffpatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab bsdiffpatcher!");
		}
		try {
			System.loadLibrary("ppfpatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ppfpatcher!");
		}
		try {
			System.loadLibrary("ips32patcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ips32patcher!");
		}
		try {
    		System.loadLibrary("glib-2.0");  
    	}
        catch( UnsatisfiedLinkError e) {
        	Log.e("Bad:","Cannot grab glib-2.0!");
        }
		try {
    		System.loadLibrary("gmodule-2.0");  
    	}
        catch( UnsatisfiedLinkError e) {
        	Log.e("Bad:","Cannot grab gmodule-2.0!");
        }
		try {
    		System.loadLibrary("edsio");  
    	}
        catch( UnsatisfiedLinkError e) {
        	Log.e("Bad:","Cannot grab edsio!");
        }
		try {
			System.loadLibrary("xdelta1patcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ips32patcher!");
		}
		try {
			System.loadLibrary("ips32patcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ips32patcher!");
		}
		try {
			System.loadLibrary("ecmpatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab ecmpatcher!");
		}
		try {
			System.loadLibrary("dpspatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab dpspatcher!");
		}
		try {
			System.loadLibrary("asmpatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab asmpatcher!");
		}
		try {
			System.loadLibrary("dldipatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab dldipatcher!");
		}
		try {
			System.loadLibrary("xpcpatcher");  
		}
		catch( UnsatisfiedLinkError e) {
			Log.e("Bad:","Cannot grab xpcpatcher!");
		}
		setContentView(R.layout.main);
		
		c = (CheckBox) findViewById(R.id.backupCheckbox);
		d = (CheckBox) findViewById(R.id.altNameCheckbox);
		r = (CheckBox) findViewById(R.id.ignoreCRC);
		e = (CheckBox) findViewById(R.id.fileExtCheckbox);
		ed = (EditText) findViewById(R.id.txtOutFile);
		
		final Button romButton = (Button) findViewById(R.id.romButton);
		romButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Globals.mode=true;
				Intent intent = new Intent( staticThis, FileBrowserActivity.class );
				intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
				startActivityForResult( intent,1 );


			}
		});
		final Button patchButton = (Button) findViewById(R.id.patchButton);
		patchButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Globals.mode=false;
				Intent intent = new Intent( staticThis, FileBrowserActivity.class );
				intent.setFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP );
				startActivityForResult( intent, 1 );
			}
		});
		final ImageButton bkHelp = (ImageButton) findViewById(R.id.backupHelp);
		bkHelp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog dialog = new AlertDialog.Builder(staticThis).create();
			    dialog.setTitle(getResources().getString(R.string.bkup_rom));
			    dialog.setMessage(getResources().getString(R.string.bkup_rom_desc));
			    dialog.setCancelable(true);
			    dialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int buttonId) {

			        }
			    });
			    dialog.show();
			}
		});
		final ImageButton altNameHelp = (ImageButton) findViewById(R.id.outfileHelp);
		altNameHelp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog dialog = new AlertDialog.Builder(staticThis).create();
			    dialog.setTitle(getResources().getString(R.string.rename1));
			    dialog.setMessage(getResources().getString(R.string.rename_desc));
			    dialog.setCancelable(true);
			    dialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int buttonId) {

			        }
			    });
			    dialog.show();
			}
		});
		final ImageButton chkHelp = (ImageButton) findViewById(R.id.ignoreHelp);
		chkHelp.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				AlertDialog dialog = new AlertDialog.Builder(staticThis).create();
			    dialog.setTitle(getResources().getString(R.string.ignoreChks));
			    dialog.setMessage(getResources().getString(R.string.ignoreChks_desc));
			    dialog.setCancelable(true);
			    dialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int buttonId) {

			        }
			    });
			    dialog.show();
			}
		});
		
		InputFilter filter = new InputFilter() { 
	        public CharSequence filter(CharSequence source, int start, int end, 
	Spanned dest, int dstart, int dend) { 
	                for (int i = start; i < end; i++) { 
	                	String IC = "*/*\n*\r*\t*\0*\f*`*?***\\*<*>*|*\"*:*";
	                        if (IC.contains("*"+source.charAt(i)+"*")) { 
	                                return ""; 
	                        } 
	                } 
	                return null; 
	        } 
		};
		ed.setFilters(new InputFilter[]{filter});
		c.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(((CheckBox)v).isChecked())
				{
					d.setEnabled(true);
					if(d.isChecked())
					{
						ed.setEnabled(true);
						e.setEnabled(true);
					}
				}else{
					d.setEnabled(false);
					ed.setEnabled(false);
					e.setEnabled(false);
				}
			}});
		d.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(((CheckBox)v).isChecked())
				{
					ed.setEnabled(true);
					e.setEnabled(true);
				}else{				
					e.setEnabled(false);
					ed.setEnabled(false);
				}
			}});
		final Button applyButton = (Button) findViewById(R.id.applyPatch);
		applyButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Warn about patching archives.
				if(Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".7z")||Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".zip")||Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".rar"))
				{
					AlertDialog dialog = new AlertDialog.Builder(staticThis).create();
				    dialog.setTitle(getResources().getString(R.string.warning));
				    dialog.setMessage(getResources().getString(R.string.zip_warning_desc));
				    dialog.setCancelable(false);
				    dialog.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int buttonId) {
				        	
				        	patchCheck();
				        }
				    });
				    dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
				        public void onClick(DialogInterface dialog, int buttonId) {
				          Toast t  = Toast.makeText(staticThis, getResources().getString(R.string.nopatch), Toast.LENGTH_SHORT);
				          t.show();
				          
				        }
				    });
				    dialog.show();
				}else{
					patchCheck();
				}
				
				
				
			}
		});
	}
	
	private void patchCheck()
	{
			if(new File(Globals.fileToPatch+".bak").exists()||(Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".ecm")&&new File(Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('.'))).exists())||new File(Globals.fileToPatch+".new").exists()||(new File(Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('/')+1)+ed.getText().toString()+((e.isChecked())?((Globals.fileToPatch.lastIndexOf('.')>-1)?Globals.fileToPatch.substring(Globals.fileToPatch.lastIndexOf('.'),Globals.fileToPatch.length()):""):"")).exists()&&d.isChecked()&&c.isChecked()))
			{
				// Just try to interpret the following if statement. I dare you.
        		System.out.println("bad");
				AlertDialog dialog2 = new AlertDialog.Builder(staticThis).create();
			    dialog2.setTitle(getResources().getString(R.string.warning));
			    dialog2.setMessage(getResources().getString(R.string.warning_desc));
			    dialog2.setCancelable(false);
			    dialog2.setButton(DialogInterface.BUTTON_POSITIVE, getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int buttonId) {
			            new File(Globals.fileToPatch+".bak").delete();
			            new File(Globals.fileToPatch+".new").delete();
			            if(Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".ecm"))
			            	new File(Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('.'))).delete();
			            new File(Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('/')+1)+ed.getText().toString()+((e.isChecked())?((Globals.fileToPatch.lastIndexOf('.')>-1)?Globals.fileToPatch.substring(Globals.fileToPatch.lastIndexOf('.'),Globals.fileToPatch.length()):""):"")).delete();
			            if(d.isChecked()&&c.isChecked()&&e.isChecked()&&Globals.fileToPatch.lastIndexOf('.')>-1)
		            	{
							ed.setText(ed.getText()+Globals.fileToPatch.substring(Globals.fileToPatch.lastIndexOf('.'),Globals.fileToPatch.length()));
		            	}
						patch(c.isChecked(), d.isChecked(), r.isChecked(), ed.getText().toString());
			        }
			    });
			    dialog2.setButton(DialogInterface.BUTTON_NEGATIVE, getResources().getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int buttonId) {
			          Toast t  = Toast.makeText(staticThis, getResources().getString(R.string.nopatch), Toast.LENGTH_SHORT);
			          t.show();
			        }
			    });
			    dialog2.show();	
			}else{
				if(d.isChecked()&&c.isChecked()&&e.isChecked()&&Globals.fileToPatch.lastIndexOf('.')>-1)
            	{
					ed.setText(ed.getText()+Globals.fileToPatch.substring(Globals.fileToPatch.lastIndexOf('.'),Globals.fileToPatch.length()));
            	}
				patch(c.isChecked(), d.isChecked(), r.isChecked(), ed.getText().toString());
			}
		
	}
	private boolean isPackageInstalled(String packagename, Context context) {
	    PackageManager pm = context.getPackageManager();
	    try {
	        pm.getPackageInfo(packagename, PackageManager.GET_ACTIVITIES);
	        return true;
	    } catch (NameNotFoundException e) {
	        return false;
	    }
	}
	public void patch(final boolean c, final boolean d, final boolean r, final String ed)
	{
		final ProgressDialog myPd_ring=ProgressDialog.show(staticThis, getResources().getString(R.string.wait), getResources().getString(R.string.wait_desc), true);
        myPd_ring.setCancelable(false);
			new Thread(new Runnable() {
		        public void run(){
		        	if(new File(Globals.patchToApply).exists()&& new File(Globals.fileToPatch).exists()&& !Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".ecm")){
		        		String msg=getResources().getString(R.string.success);
						if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".ups"))
						{
							int e = upsPatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new", r ? 1 : 0);
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_UPS);
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xdelta")||Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xdelta3")||Globals.patchToApply.toLowerCase(Locale.US).endsWith(".vcdiff")){
							RandomAccessFile f = null;
							try { 
								f = new RandomAccessFile(Globals.patchToApply, "r");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							StringBuilder s = new StringBuilder();
							try {
								if(f.length()>=9)
								{
									for(int i = 0; i<8; i++)
									{
										s.append((char)f.readByte());
										f.seek(i+1);
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								f.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							// Header of xdelta patch determines version
							if(s.toString().equals("%XDELTA%")||s.toString().equals("%XDZ000%")||s.toString().equals("%XDZ001%")||s.toString().equals("%XDZ002%")||s.toString().equals("%XDZ003%")||s.toString().equals("%XDZ004%"))
							{
								int e = xdelta1PatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
								if(e!=0)
								{
									msg=parseError(e, Globals.TYPE_XDELTA1);
								}
							}else{
							int e = xdelta3PatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_XDELTA3);
							}
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".bps")){
							int e = bpsPatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new", r ? 1 : 0);
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_BPS);
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".dps"))
						{
							int e = dpsPatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
							if(e!=0)
							{
								msg=parseError(e,Globals.TYPE_DPS);
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".bsdiff")){
							int e = bsdiffPatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_BSDIFF);
							}
							
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".aps")){
							File f = new File(Globals.fileToPatch);
							File f2 = new File(Globals.fileToPatch+".bak");
							try {
								Files.copy(f,f2);
							} catch (IOException e) {
								e.printStackTrace();
							}
							byte[] gbaSig = {0x41, 0x50, 0x53, 0x31, 0x00};
							byte[] n64Sig = {0x41, 0x50, 0x53, 0x31, 0x30};
							byte[] realSig = new byte[5];
							RandomAccessFile raf = null;
							System.out.println("APS Patch");
							try
							{
								raf = new RandomAccessFile(Globals.patchToApply, "r");
							} catch (FileNotFoundException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							try
							{
								raf.read(realSig);
							} catch (IOException e1)
							{
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							if(Arrays.equals(realSig, gbaSig))
							{
								System.out.println("GBA APS");
								APSGBAPatcher aa = new APSGBAPatcher();
								aa.crcTableInit();
								int e = 0;
								try
								{
									e = aa.ApplyPatch(Globals.patchToApply, Globals.fileToPatch, r);
								} catch (IOException e1)
								{
									// TODO Auto-generated catch block
									e1.printStackTrace();
									e = -5;
								}
								System.out.println("e: "+e);
								if(e!=0)
								{
									msg=parseError(e, Globals.TYPE_APSGBA);
								}
							}else if(Arrays.equals(realSig, n64Sig))
							{
								System.out.println("N64 APS");
								int e = apsN64PatchRom(Globals.fileToPatch, Globals.patchToApply);
								if(e!=0)
								{
									msg=parseError(e, Globals.TYPE_APSN64);
								}
							}else{
								msg=parseError(-131, -10000);
							}
							
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".ppf")){
							File f = new File(Globals.fileToPatch);
							File f2 = new File(Globals.fileToPatch+".bak");
							try {
								Files.copy(f,f2);
							} catch (IOException e) {
								e.printStackTrace();
							}
							int e = ppfPatchRom(Globals.fileToPatch, Globals.patchToApply, r ? 1 : 0);
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_PPF);
							}
							
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".patch")){
							int e = xdelta1PatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_XDELTA1);
							}	
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".asm"))
						{
							File f = new File(Globals.fileToPatch);
							File f2 = new File(Globals.fileToPatch+".bak");
							try {
								Files.copy(f,f2);
							} catch (IOException e) {
								e.printStackTrace();
							}
							int e = asmPatchRom(Globals.fileToPatch, Globals.patchToApply);
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_ASM);
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".dldi"))
						{
							File f = new File(Globals.fileToPatch);
							File f2 = new File(Globals.fileToPatch+".bak");
							try {
								Files.copy(f,f2);
							} catch (IOException e) {
								e.printStackTrace();
							}
							int e = dldiPatchRom(Globals.fileToPatch, Globals.patchToApply);
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_DLDI);
							}
						}else if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xpc"))
						{
							int e = xpcPatchRom(Globals.fileToPatch, Globals.patchToApply, Globals.fileToPatch+".new");
							if(e!=0)
							{
								msg=parseError(e, Globals.TYPE_XPC);
							}
						}else{
							RandomAccessFile f= null;
							try {
								f = new RandomAccessFile(Globals.patchToApply, "r");
							} catch (FileNotFoundException e1) {
								e1.printStackTrace();
							}
							StringBuilder s = new StringBuilder();
							try {
								if(f.length()>=6)
								{
									for(int i =0; i<5; i++)
									{
										s.append((char)f.readByte());
										f.seek(i+1);
									}
								}
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							try {
								f.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
							int e;
							// Two variants of IPS, the normal PATCH type, then this weird one called IPS32 with messily hacked in 32 bit offsets
							if(s.toString().equals("IPS32"))
							{
								e = ips32PatchRom(Globals.fileToPatch, Globals.patchToApply);
								if(e!=0)
								{
									msg=parseError(e, Globals.TYPE_IPS);
								}
							}else{
								 e = ipsPatchRom(Globals.fileToPatch, Globals.patchToApply);
									if(e!=0)
									{
										msg=parseError(e, Globals.TYPE_IPS);
									}
							}
							
						}
						if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".asm")&&!Globals.didAnAsm)
							Globals.didAnAsm=true;
						if(Globals.patchToApply.toLowerCase(Locale.US).endsWith(".ups")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xdelta")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xdelta3")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".vcdiff")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".patch")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".bps")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".bsdiff")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".dps")||
								Globals.patchToApply.toLowerCase(Locale.US).endsWith(".xpc"))
						{
							File oldrom = new File(Globals.fileToPatch);
							File bkrom = new File(Globals.fileToPatch+".bak");
							oldrom.renameTo(bkrom);
							File newrom = new File(Globals.fileToPatch+".new");
							newrom.renameTo(oldrom);
						}
						if(!c)
						{
							File f = new File(Globals.fileToPatch+".bak");
							if(f.exists())
							{
								f.delete();
							}
						}else{
							if(d)
							{
								File one = new File(Globals.fileToPatch+".bak");
								File two = new File(Globals.fileToPatch);
								File three = new File(Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('/')+1)+ed);
								Log.e("Swegh", "Renaming to: "+three.getAbsolutePath());
								
								two.renameTo(three);
								File four = new File(Globals.fileToPatch);
								one.renameTo(four);	
							}
						}
						Globals.msg=msg;
					}else if(Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".ecm"))
					{
						int e = 0;
						String msg=getResources().getString(R.string.success);
						if(c)
						{
							
							if(d)
							{
								e=ecmPatchRom(Globals.fileToPatch,Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('/'))+ed,1);
							}else{
								//new File(Globals.fileToPatch).renameTo(new File(Globals.fileToPatch+".bak"));
								e=ecmPatchRom(Globals.fileToPatch,Globals.fileToPatch.substring(0,Globals.fileToPatch.lastIndexOf('.')),1);
							}
						}else{
							e=ecmPatchRom(Globals.fileToPatch, "",0);
						}
						if(e!=0)
						{
							msg=parseError(e, Globals.TYPE_ECM);
						}
						Globals.msg=msg;
		        	}else{
						Globals.msg=getResources().getString(R.string.fnf);
					}
		        	
		        }
		    }).start();
			new Thread(new Runnable() {                 
				@Override
				public void run() {
				try
				   {
				    while(Globals.msg.equals(""));
				    myPd_ring.dismiss();
				    runOnUiThread(new Runnable() {
				        public void run() {
				        	Toast t = Toast.makeText(staticThis, Globals.msg, Toast.LENGTH_SHORT);
						    t.show();
				        }
				    });
				    final SharedPreferences prefs = PreferenceManager
				            .getDefaultSharedPreferences(MainActivity.this);
				    int x = prefs.getInt("purchaseNag", 5);
				    if(x!=-1)
				    {
				    	if(isPackageInstalled("com.xperia64.timidityae", MainActivity.this))
				    	{
				    		prefs.edit().putInt("purchaseNag", -1);
				    	}else{
				    	
				    /*if(x>=5)
				    {
				    	
				    	prefs.edit().putInt("purchaseNag", 0).commit();
				    	runOnUiThread(new Runnable() {
					        public void run() {
				    	AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
				    	b.setTitle("Timidity AE");
				    	b.setIcon(getResources().getDrawable(R.drawable.timidity));
				    	b.setMessage(getResources().getString(R.string.nagMsg));
				    	b.setCancelable(false);
				    	b.setNegativeButton(getResources().getString(android.R.string.cancel), new OnClickListener(){@Override public void onClick(DialogInterface arg0, int arg1) {}});
				    	b.setPositiveButton(getResources().getString(R.string.nagInfo), new OnClickListener(){

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								try {
								    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.xperia64.timidityae")));
								} catch (android.content.ActivityNotFoundException anfe) {
								    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=com.xperia64.timidityae")));
								}
							}
				    		});
				    			b.create().show();
					        	}
				    		}); // end of UIThread
				    	}else{
				    		prefs.edit().putInt("purchaseNag", x+1).commit();
				    	}*/
				    }
				    }
				    Thread.sleep(3000); // Wait to clear Globals.msg
				    Globals.msg="";
				  }catch(Exception e){}
				 }
				 }).start();
	}
	
	@Override
	public boolean onKeyDown(int KeyCode, KeyEvent event) {
		if(KeyCode == KeyEvent.KEYCODE_BACK)
		{
			System.exit(0);
		}
		return true;
	}

	// Error message based on error code.
	public String parseError(int e, int t)
	{
		switch(t)
		{
		case Globals.TYPE_UPS:
			switch(e)
			{
			case -1:
				return getResources().getString(R.string.upsNegativeOne);
			case -2:
				return getResources().getString(R.string.upsNegativeTwo);
			case -3:
				return getResources().getString(R.string.upsNegativeThree);
			case -4:
				return getResources().getString(R.string.upsNegativeFour);
			case -5:
				return getResources().getString(R.string.upsNegativeFive);
			case -6:
				return getResources().getString(R.string.upsNegativeSix);
			case -7:
				return getResources().getString(R.string.upsNegativeSeven);
			default:
				return getResources().getString(R.string.upsDefault)+e;
			}
		case Globals.TYPE_XDELTA3:
			switch(e)
			{
			case -17710:
				return getResources().getString(R.string.xdelta3NegativeSeventeenThousandSevenHundredTen);
			case -17711:
				return getResources().getString(R.string.xdelta3NegativeSeventeenThousandSevenHundredEleven);
			case -17712:
				return getResources().getString(R.string.xdelta3NegativeSeventeenThousandSevenHundredTwelve);
			default:
				return getResources().getString(R.string.xdelta3Default)+e;	
			}
		case Globals.TYPE_XDELTA1:
			switch(e)
			{
			case 2:
				return getResources().getString(R.string.xdelta1Two);
			case 3:
				return getResources().getString(R.string.xdelta1Three);
			case 4:
				return getResources().getString(R.string.xdelta1Four);
			case 5:
				return getResources().getString(R.string.xdelta1Five);
			case 6:
				return getResources().getString(R.string.xdelta1Six);
			case 7:
				return getResources().getString(R.string.xdelta1Seven);
			case 8:
				return getResources().getString(R.string.xdelta1Eight);
			case 9:
				return getResources().getString(R.string.xdelta1Nine);
			case 10:
				return getResources().getString(R.string.xdelta1Ten);
			case 11:
				return getResources().getString(R.string.xdelta1Eleven);
			case 12:
				return getResources().getString(R.string.xdelta1Twelve);
			case 13:
				return getResources().getString(R.string.xdelta1Thirteen);
			default:
				return getResources().getString(R.string.xdelta1Default)+e;	
			}
		case Globals.TYPE_BPS:
			switch(e)
			{
			case 1:
				return getResources().getString(R.string.bpsOne);
			case -1:
				return getResources().getString(R.string.bpsNegativeOne);
			case -2:
				return getResources().getString(R.string.bpsNegativeTwo);
			case -3:
				return getResources().getString(R.string.bpsNegativeThree);
			case -4:
				return getResources().getString(R.string.bpsNegativeFour);
			case -5:
				return getResources().getString(R.string.bpsNegativeFive);
			case -6:
				return getResources().getString(R.string.bpsNegativeSix);
			case -7:
				return getResources().getString(R.string.bpsNegativeSeven);
			case -8:
				return getResources().getString(R.string.bpsNegativeEight)+e;
			default:
				return getResources().getString(R.string.bpsDefault)+e;
			}
		case Globals.TYPE_BSDIFF:
			switch(e)
			{
			case 1:
				return getResources().getString(R.string.bsdiffOne);
			case 2:
				return getResources().getString(R.string.bsdiffTwo);
			default:
				return getResources().getString(R.string.bsdiffDefault)+e;
			}
		case Globals.TYPE_PPF:
			switch(e)
			{
			case 1:
				return getResources().getString(R.string.ppfOne);
			case -1:
				return getResources().getString(R.string.ppfNegativeOne);
			case -2:
				return getResources().getString(R.string.ppfNegativeTwo);
			case -3:
				return getResources().getString(R.string.ppfNegativeThree);
			case -4:
				return getResources().getString(R.string.ppfNegativeFour);	
			default:
				return getResources().getString(R.string.ppfDefault)+e;
			}
			case Globals.TYPE_IPS:
				switch(e)
				{
				case 1:
					return getResources().getString(R.string.ipsOne);
				case -1:
					return getResources().getString(R.string.ipsNegativeOne);
				case -2:
					return getResources().getString(R.string.ipsNegativeTwo);
				case -3:
					return getResources().getString(R.string.ipsNegativeThree);
				case -4:
					return getResources().getString(R.string.ipsNegativeFour);
				case -5:
					return getResources().getString(R.string.ipsNegativeFive);
				case -6:
					return getResources().getString(R.string.ipsNegativeSix);
				case -7:
					return getResources().getString(R.string.ipsNegativeSeven);
				default:
					return getResources().getString(R.string.ipsDefault)+e;
				}
			case Globals.TYPE_IPS32:
				switch(e)
				{
				case 1:
					return getResources().getString(R.string.ips32One);
				case -1:
					return getResources().getString(R.string.ips32NegativeOne);
				case -2:
					return getResources().getString(R.string.ips32NegativeTwo);
				case -3:
					return getResources().getString(R.string.ips32NegativeThree);
				case -4:
					return getResources().getString(R.string.ips32NegativeFour);
				case -5:
					return getResources().getString(R.string.ips32NegativeFive);
				case -6:
					return getResources().getString(R.string.ips32NegativeSix);
				case -7:
					return getResources().getString(R.string.ips32NegativeSeven);
				default:
					return getResources().getString(R.string.ips32Default)+e;
				}
			case Globals.TYPE_ECM:
				switch(e)
				{
				case 1:
					return getResources().getString(R.string.ecmOne);
				case 2:
					return getResources().getString(R.string.ecmTwo);
				case 3:
					return getResources().getString(R.string.ecmThree);
				case 4:
					return getResources().getString(R.string.ecmFour);
				case 5:
					return getResources().getString(R.string.ecmFive);
				default:
					return getResources().getString(R.string.ecmDefault)+e;
				}
			case Globals.TYPE_DPS:
				switch(e)
				{
				case -1:
					return getResources().getString(R.string.dpsNegativeOne);
				case -2:
					return getResources().getString(R.string.dpsNegativeTwo);
				case -3:
					return getResources().getString(R.string.dpsNegativeThree);
				default:
					return getResources().getString(R.string.dpsDefault)+e;
				}
			case Globals.TYPE_ASM:
				switch(e)
				{
				default:
					return getResources().getString(R.string.asmDefault)+e;
				}
			case Globals.TYPE_DLDI:
				switch(e)
				{
				default:
					return getResources().getString(R.string.dldiDefault)+e;
				}
			case Globals.TYPE_APSN64:
				switch(e)
				{
				default:
					return getResources().getString(R.string.apsn64Default)+e;
				}
			case Globals.TYPE_APSGBA:
				switch(e)
				{
				case -1:
					return getResources().getString(R.string.apsgbaNegativeOne);
				case -2:
					return getResources().getString(R.string.apsgbaNegativeTwo);
				case -3:
					return getResources().getString(R.string.apsgbaNegativeThree);
				case -4:
					return getResources().getString(R.string.apsgbaNegativeFour);
				case -5:
					return getResources().getString(R.string.apsgbaNegativeFive);
				default:
					return getResources().getString(R.string.apsgbaDefault)+e;
				}
			case Globals.TYPE_XPC:
				switch(e)
				{
				default:
					return getResources().getString(R.string.xpcDefault)+e;
				}
			default:
				return getResources().getString(R.string.errorDefault);
		}
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				TextView tv1 = (TextView) findViewById(R.id.romText);
				TextView tv2 = (TextView) findViewById(R.id.patchText);
				if(Globals.fileToPatch.lastIndexOf("/")>-1)
				{
					tv1.setText(Globals.fileToPatch.substring(Globals.fileToPatch.lastIndexOf("/")+1));
				}
				if(Globals.fileToPatch.toLowerCase(Locale.US).endsWith(".ecm"))
				{
					tv2.setEnabled(false);
					((Button) findViewById(R.id.patchButton)).setEnabled(false);
				}else{
					tv2.setEnabled(true);
					((Button) findViewById(R.id.patchButton)).setEnabled(true);
				if(Globals.patchToApply.lastIndexOf("/")>-1)
				{
					tv2.setText(Globals.patchToApply.substring(Globals.patchToApply.lastIndexOf("/")+1));
				}
				}
				tv1.postInvalidate();
				tv2.postInvalidate();
			}
		}
	}
}
