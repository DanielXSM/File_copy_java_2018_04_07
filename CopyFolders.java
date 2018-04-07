package sinsoft;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class CopyFolders {
	public static void main(String[] args) {
		String old[]=new String[]{"S:\\ps1","S:\\ps2"};
		String newPath="E:\\";
		copyFolders( newPath,old);
	}

	/**zdp
	 */
	public static void copyFolders(String newPath,String ... oldPath) {
		for(String a:oldPath){
			singleCopy t=new singleCopy(a,newPath);
			Thread t1=new Thread( t);
			t1.start();
		}
		System.err.println("game  over");
	}
	
	static class  singleCopy implements Runnable{
		private String oldPath;
		private String newPath;
		
		singleCopy(String oldPath,String newPath){
		this.oldPath=oldPath;
		this.newPath=newPath;
		}
		@Override
		public void run() {
			try {
				findDirectory( oldPath, newPath);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		public static void findDirectory(String oldPath,String newPath) throws Exception{
			//todo 原路径
			File file=new File(oldPath);
			String originame=file.getName();
//			BufferedReader br=null;
//			BufferedWriter bw=null;
			OutputStream o=null;
			InputStream fn=null;
			if(file.exists()){
				String a=file.getPath();
				File fileNew=new File(newPath);
				if(!fileNew.exists()){
					fileNew.mkdirs();
				}
				File fileNewCopy=new File(newPath+File.separator+originame);
				if(!fileNewCopy.exists()){
					fileNewCopy.mkdirs();
				}
				
				File[]files=file.listFiles();
				
				for(File f:files){
//					System.err.println("文件的名称"+f.getName()+"文件的编码:");
					if(f.isDirectory()){
							findDirectory(f.getPath(), fileNewCopy.getPath());
//						}
					}else {
						String abc=f.getName();
						//写出去的文件
						File ft=new File(fileNewCopy.getPath()+File.separator+abc);
//						InputStream fn=new FileInputStream(f.getPath());
						/*InputStream in=new FileInputStream(f);
//						BufferedInputStream  bfi=new BufferedInputStream(in);
						InputStreamReader ir=new InputStreamReader(in, "gbk");
//						Reader r=new InputStreamReader(in);
						br=new BufferedReader(ir);
//						 br = new BufferedReader(new FileReader(f));
						OutputStreamWriter osw=new OutputStreamWriter(new FileOutputStream(ft), "gbk");  
						 bw=new BufferedWriter(osw);
						 String str;
						 while ((str = br.readLine())!= null) // 判断最后一行不存在，为空结束循环
						 {
								bw.write(str);
								bw.newLine();
						 }
						bw.flush();
						bw.close();*/
						fn=new FileInputStream(f.getPath());
						o=new FileOutputStream(ft);
						byte[] b = new byte[1024 * 10];
						int nu=0;
						while((nu=fn.read(b))!=-1){
							o.write(b, 0, nu);
						}
						o.flush();
						o.close();
						fn.close();
					}
				}
				
				if(null!=fn){
					fn.close();
			
			}
				if(null!=o){
					o.close();
				}
			}
			
			
			
			
		}
		
	}
	
	
	
	
}
