package ResponstMappingTest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Test01 {
    public ResponseEntity<byte[]> test01(HttpSession session ) throws IOException {
        //获取servletContext
        ServletContext servletContext = session.getServletContext();
        //获取文件中的具体路径||路径可不写死
        String realPath = servletContext.getRealPath("/static/img/1.jpg");
        //创建流
        FileInputStream is = new FileInputStream(realPath);
        //创建字节数组
        //is.available()获取字节数
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> httpHeaders = new HttpHeaders();
        //设置下载方式及下载文件的名字
        httpHeaders.add("Content-Disposition","attachment;filename=1.jpg");
        //设置响应状态码
        HttpStatus ok = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity responseEntity = new ResponseEntity<byte[]>(bytes,httpHeaders,ok);
        is.close();
        return responseEntity;
    }


}
