import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;

public class GetImage {

    public static void main(String[] args){

        GetImage getImage = new GetImage();
        Scanner scanner = new Scanner(System.in);
        System.out.println("해당 프로그램은 인터넷에 돌아댕기는 사진중 연속적으로 이름이 같은 파일만 먹히는 프로그램입니다.");
        System.out.println("URL을 입력해주세요 \nEX) 만약 주소가 https://github.com/file/asd/22.jpg라면 \n https://github.com/file/asd/ 를 입력해주세요.");
        String url = scanner.nextLine();
        System.out.println("시작페이지 쪽수를 입력하세요 \nEX)1");
        String startNumber = scanner.nextLine();
        System.out.println("끝나는페이지를 입력해주세요 \nEX) 100");
        String endNumber = scanner.nextLine();
        System.out.println("확장자를 입력해주세요  \nEX) jpg");
        String extension = scanner.nextLine();
        System.out.println("로컬저장소 주소를 입력해주세요\nEX) C:/tmp/");
        String savePoint = scanner.nextLine();
        try {

            getImage.saveImage(url,startNumber,endNumber,extension,savePoint);

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private void saveImage(String url1,String startNumber, String endNumber, String extension,String savePoint) throws IOException {

        URL url = null;
        InputStream in = null;
        OutputStream out = null;
        int i=1;
        try {
            for(i = Integer.parseInt(startNumber);i<=Integer.parseInt(endNumber);i++) {
                String strUrl = url1+i+"."+extension; //불러올 URL
                url = new URL(strUrl);

                in = url.openStream();
                out = new FileOutputStream(savePoint+i+"."+extension); //저장경로

                while (true) {
                    //이미지를 읽어온다.
                    int data = in.read();
                    if (data == -1) {
                        break;
                    }
                    //이미지를 쓴다.
                    out.write(data);

                }
                in.close();
                out.close();
            }


        } catch (Exception e) {

            e.printStackTrace();

        }finally{

            if(in != null){in.close();}
            if(out != null){out.close();}

        }
    }

}