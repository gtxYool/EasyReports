/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package easyreport.utils;

import javax.xml.bind.DatatypeConverter;
import java.io.*;

/**
 * Clase experimental para generar el logo por medio de base64 en lugar de
 * buscarlo en disco
 *
 * @author DYOOL
 */
public class ImageConverter {

    /**
     * Crea una imagen desde un String base64 de la imagen
     *
     * @param base64String IMAGEN en base64
     * @param name nombre que tendra el archivo
     * @param path ruta donde se guardará el archivo
     * @return true si se creo, false si ocurrio una exception.
     * @throws IOException exception
     */
    public boolean b64ToIMAGE(String base64String, String name, String path) throws IOException {
        name = name.contains(".") ? name.split(".")[0] : name.trim();
        String[] strings = base64String.split(",");
        String extension;
        switch (strings[0]) {//check image's extension
            case "data:image/jpeg;base64":
                extension = ".jpeg";
                break;
            case "data:image/png;base64":
                extension = ".png";
                break;
            default://should write cases for more images types
                extension = ".jpg";
                break;
        }
        //convert base64 string to binary data
        byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
        path = path + name + extension;
        File file = new File(path);
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(data);
            outputStream.flush();
            //Sonar lint dice que:
            //Java 7’s try-with-resources structure automatically handles closing the resources that the try itself opens.
            // Thus, adding an explicit close() call is redundant and potentially confusing.
            // por tanto retire el "outputStream.close()" del metodo haha1
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Crea una imagen desde un String base64 de la imagen
     *
     * @param base64String IMAGEN en base64
     * @return File de la imagen, null si algo salio mal.
     */
    public byte[] b64ToIMAGEFile(String base64String) {
        String[] strings = base64String.split(",");
        return DatatypeConverter.parseBase64Binary(strings[1]);
    }

    /**
     * logo de guatex en base64
     *
     * @return the LOGOGUATEX base64
     */
    public String getLOGOGUATEX() {
        return "data:image/jpeg;base64,"
                + "iVBORw0KGgoAAAANSUhEUgAAAKAAAABcCAYAAADpn75PAAAAAXNSR0"
                + "IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAADi5SURBVHhe7X0HQB"
                + "VX2rYm2W//7G42u9nst0lMbCjSO9hQsPfYNZZEY9fYCx0pAqKI2BV7bLH3ihV7xIbSe+/1wr10nv"
                + "89Z+Zy76VYEHXNx6MvZ+bMKXNnnnnf855z5kwT/B9CRFS0uNWI+uBtXL//UwRsxH8fGgnYiPeKRg"
                + "I24r3iD0nAyspKVLCQpJzH1A52nEkj3h/+mAQkKSkrhSQhDtn3biPz7ClkHT6MzONHkXH9ErKDn6"
                + "EwJxtlQvJGvEf8IQgo13aMUDmPA5Cw0h0Ro4YjvIMxIrTVEd6uFSLbtER429YI02iNUAMdhPfohr"
                + "hZs5F0+BAkWRmsGF5OI94t/hAEZGY245Y/Iif/jHBtbUSpfYdYLXXEEtFijXQRa6ynInGGFK+ngR"
                + "j1VgglYoZZmCNhjRcKszOFAjkajfO7wAdLwEqRILLsDITbWSNUQxPR6i0RY6SHKFNDRJoaINpUH9"
                + "EmSqK8T9uRJFGULlJPB6FqLRDWwwLp509zTVpO5VdQW7IRbxcfJAHltMgJeoqgAT0RodaMiCcnFS"
                + "NgbcLIVjOekTCCCMtIG6XTFsEazRHt5YrSigqhnkYSvlU0+dBGBxgdmGQG3EVQR1NEaqoh2oyR62"
                + "ViUEtcdSFtaKxL2vA7RFvNQyk5MuXyChvxVvBBasC8sBA8NzdCjLZIPhIhJK0m7gtxpOHakyk20k"
                + "REu5aI0mqDGDGNPL0inyCR7ZlmJCdF7RtEOls3espvGR8cAaX5uQga1g+R2i0Q2UGHCMNEG5FmJL"
                + "Qd1UEXUUYanHTR7XURbayB4O5miPIkz3jmTIQZaiCqvZaQR56X5VMSth9tpoMgjW+RuH+XWHMj3g"
                + "Y+GAIyK8i6SWI8liJM82vEdCaSdBIkqhORqKMWbWsj2lANYQO7ILRvF0QZ0DaRMeXSGV6GjCTil2"
                + "mI1mtFRNVCVEfymJlQvihejiC8THMqy7QtQjtoIy8igudvtMUNjw9KA2YHPsbz9uqI66yJGAsdFY"
                + "kliTRpjWjb+ZDlZyI/KwVh08YixKwdCpMTxBKAlE3rEKXXgtJrIbarNoXaNcqqEksyyXrNELl4pm"
                + "iKGwnY0PhgCMj6+iLs5pO32wzx3fQQ101XVSy1EdahDXICHwgZCBlnDyHQtCUk8ZFiDJC0aRWZ6O"
                + "8Q014N4Z3bkMltRSTUQKxYZqyljlKZOkjoQuV2bI2swACxhEY0JD4IAjK9kxcbjrCejBRElh5Elp"
                + "6isG2S2B66CCci5T9XEDDrwmE87dQGkgQFARN9VyBU50tEOc5H9vOHSDy0A5HdiXRMI1IZTHiZVD"
                + "bfpjDKrDmil9uIJTSiIfHBaMCU/b6IMf0PovuQyexDROlNbTUSts2lN2mqburIf/a7mAPIvEAasG"
                + "trFCbI23CkATcvR7BlWxRlZ/N9Ru5op5mI7txcURaJctmx3dohaExHFOZni8N1jaa4ofBBEJDd9C"
                + "jbyYiy/BbRA8m7HUhttwFExAFaiKFtJtEDNBHWuy0kyhrw4iHygNUgTVQQMHWTJ4KHGKJYmi/GkF"
                + "Zcb4dIi2ZiWaxMEqWyY6ns8O4tkfXgipijEQ2F/24CkqJhukZamIeIiRZEhDaIHUqEYDJEG3FDyf"
                + "kg4eEQLYQObIv8YIUGzL54GMG9WkGmpAGTt3oidJguigtyxBhGQHtE9mjGyxRELHcYK5fVp8WPpx"
                + "7eIOZoREPhrROworIMkZGJuH7tGU6fuY/zFx4g4GEEcnMkYooXQLR0kvgIRI4mh2CYOuJHaFVJwk"
                + "gKSRLY/nANRAwhDRiiIGCO3yGED2gBmZIGTNm2HBEj9VBcqCBgykZHxPb7lpeXMFJbSeR1kIbt9z"
                + "US1y6pmnnTiIbBWyCgMAU0JzcXG7ecxdCRXjA2tYemlj3UNezQjkRHzwEW3d2w0GovHgWG8/QvQl"
                + "7oQ8SMUkfSOJLxWkgcp02iRdtK4XgyzaPI4Qi9L+YCcq/+hvBhzSFNiawiTdoOFyKzOmSFuWIMmW"
                + "VfB9Jy3yBxLBFunCaJBi9XLglUX/zQZkjwnPzCCa6NeH28FQ145WoAevZ2R6s21tAmshmbusDUzB"
                + "2m7UnMPGBiuhwGRm5o284Wunq2WLnqJMorah/0YsTJf34T8WNbInmSOpIna5Jok+iIoRYPEydpIO"
                + "Yn0oBhCgLm3ziIqB++IwIqmeCjaxC8sAe1AfPEGCLldmvEjfoKiT9rIGmSNokOCZHvZ6qLwmS2T+"
                + "XEr5j0UgJmZBUgPEqC0OjCN5awaClCI2WIjsuDTMa60f94aHAC7tl3FeqaVtDRdYZZBzciHRNGPv"
                + "m2QsxIjExc0UptCebM24Hi4mKxFFXkh99H3BQ1JE8nDTizHYkmEqfrInEGkYX2E2cSSaZrIY7IKI"
                + "lQOCHZN/YibOQ/IU0I4URmE1tKiiUolGapED79V2sk/PQNlUUEJEmcIYiw347K1kXyTy2R4jPtpZ"
                + "NWFy7aBKcFg7DRoy/WLetHwsLXl/XL+pD0xRb3fvhp9FicPPVIrOGPhQYl4PETt9FW3QoGxstqJV"
                + "ztsgwmJC1aLcZyj0NiSQow4hQmhiFxgQFS52khZYEOUpnM16NQl0Sbh2nzqI04mwgYpegwlsQ9Q/"
                + "IpHxTnZ4oEVNCH7TOwmLTf7JE8tTmVzcpjZYtC2ylUPotPntYKabsXvXRywoKFaxB/5S9ABF3aoD"
                + "eUYJKEJtjorI+9vz0Ta/hjgX5hw+B5aBz0TWyhbyBottrJVpcsg6GZKzR1rfDoUahYIoFYwogik6"
                + "YjxbUHMhdpI8PaSBRDLuk8NEL6Ej0kLNKBJPaJkJcgdxh4yF9UqkAZkbCctstpm006lWbFI3HFcK"
                + "Qu0kC6jbxsRfny7eTZbZDlt6GKuHVhweK1CDv3GSoCm6Do4UdvIB+j+GFTILwJ1iw1wv5DgWINfy"
                + "w0CAGZOftxwgZoaDoS+Wo3ty8W0pgdXNFWwx7zF+2gElVvczlKkbLjZ6RbaSJjqQmJGYkpl3QxzH"
                + "A0RbK1DvLC/MVc1SFMMM26uw+p3kOQtvFHpG7+EckePZFqZ4B0JyqTCS/bTFEu1Ze51BiJNjrID7"
                + "ksFPUCCAT8u0jAj99APiECfkQEbEoENG4k4Itw6swDtGlrVwuxXlWYyXaBETkmnTs6IzU5TSxZgc"
                + "wbW5Bir44M9/bIcOsoiLsYcumAdBdySraPQ15cAKTZMZBmRqAgMxJl5UVUgkDqLL9VSF/0HdKc9L"
                + "mku5ogzaMj0pXLkm+zkOpLdzVC/KqeKJakvqIGbCTgq+KNCVheVobRP3hDS9exGqmqy6tpRtaGPH"
                + "zkplg6g3DLpWnBSPE0Q5anKTJXtkemVwcuWcqyqj3Slusj0dMYSd4dkexlivi1PSHLS6giTrb/ej"
                + "Ln7ZC+ksjlRdqO8qWTZKyk8kgyqGy+zePaI4u201x1kHZysVjCi9FIwNfDGxPw/v1QaGrbw9Sspu"
                + "PBvGBD8nLbadqhbTtrodvFwJnMrWo6hbhTWnssXLCXSlbVNWVkQjOOzULmCmqLrTFEto8hckhYmO"
                + "1jJIYUt9oQuav1kOOtjVyvdshaa4ai/Liq0nJvrUX68pbIojwsLcuTSeVlifnZNt9fY0QhK5fMsx"
                + "c5N/F1mXZVNBLw9fDG74S4uB4mrUUErEYmsw7u0NNzRoeOTljmfggHf7uKdRvOYsDg1URYuzrbiv"
                + "qGrujVxwMFBQViDQpIEvyRsqUzMraZk3Tlkr69K9J2dEXKzq7I2toV2VVxFkjf1hnJu/qSFxwrlk"
                + "Cm/MFmJG8wQOb2Lsik4xmipG/txCWNC5lkcT9lIxHwzCxyXl5tcn7DEVDZCWnUgLVCVlyE/oNWCl"
                + "qtGpH0DFzRtYsLAp/GiKkF5OfnY+r0bVxrmvG2n2o+YzMiocFSBAfFiTkEsD68UtJjJfnxKM1PQa"
                + "k0A6WFWSiWZqJYlokSWTZKpFkkmSiifRZXXMRC+QwWAWVlxSgupPSUtqgwg/Kn0z5JQSqKSFjI2n"
                + "qygjRIaVuaEwlpSQ4qqH75q6AvwosJyLTaJ0AIXfZXlcQm2LLMEPsONnbD1MDjJ1HQMbCDCR/lUC"
                + "aROzR1bXHlSu2dp4lJaUQ+Bxgbu6rkk0s7DVucP/9QTK0KRqZCWTykuYGQScIgzQ+HjAtt034RhY"
                + "JQnCSU4kMgy3mOwpxnRKZAyhcMaV4E5SORELkkLF0YSTiKJBE8lBXQdn4oCnKfE+lLhYpfES8iYM"
                + "mjj1Dw++e4trsdjq5vgWMbWpK0xrH1JBtaKUIuQvzZHc0xYVRX7DvQ2BFdA76+fmijbl2NQMugqb"
                + "MU4yeymSN1aYxKzJq5De20bVXyGpqwvE748j9zsWXLOTFtTeSlXkfCUR1kHGmDtGP6yDiqR6Irih"
                + "7F6SGdJOOIDtKP6NI2mdyjTPRIaF8Ulj6NbVOaDCY8jx6yKF/aga+RcnMaSkplr6D3FHgRAVlc5M"
                + "V/Y+RwV2zZcQtbmWy/ja3bSJTDKrmFLdtv0rW4iujoVLGGPxbeiIDTZ/rySQbKHi7rhG6rvgR79t"
                + "0QU9WOvQduQE3dhrf5mOOh0c4K5p1cMWmyLzZvvYjY+BRKVdutF+IKEo8j81Rz5B7/GgUn26DwBA"
                + "mFkpNqyDnTBnmn1FBwXA35FOacpm0K2XEup1qj4ERLStuS0ramNCy/GqQn21J8K+Qf/Dty/MehvC"
                + "ST1/U6eCEBnzZB0Om/w3ZJ47QuOepNQImkEL37eHICKRPQyISFS5GQULMvTxlBIXHQ0LJB994eWG"
                + "KzDydO3UNyUrp49EVQkLIw4zwy/DSIXJ9Ccv5rSM41o7AZ8i98h/zz36Lg3Lc8zKO4ggvfovD8dy"
                + "hg8WyfwvwLzZBLkk/HWL6Cs18QGT9FbsBMaisWvpbmk+NlBAw+8zmWLPQRU7MaXiR/fNSbgKGhsT"
                + "AyZjNdFCaUiZYumd8f11OKF19AmbQADx4Ek1NS09t9NQgugUwajKxH3yPnUhNILzZB4eW/ouDy30"
                + "j+QiLfFuWKKLQtoTDvKsmVvyL/8qfIo7w5V0mbxqxFWdW5K7svr4aXETCEE9BbTN2IehPw8uVHpM"
                + "FU23AmJG3a2cFj+Qkx1auhfs86yyWQsIIchcKUbch7qIe8600gu9oEBf5ESJKCm00gudmUwqYovP"
                + "kRCQubQkbHZNfo2BUS/79CEjyBPOPgqpLre1Yv1YCnP/+vNsFszPxdot4E3LHjGrX1VIffOAE1bH"
                + "Do6B0x1bsBu2Rsnl5ppRTSzMPIjxgLyWNq493/CJK7pBXvEBlJWFhA+yyOeaOSwC4ojHdCkfQ5n+"
                + "XSEJf+RQSspLiw8//CmNGrceZCDM5eiMDZ8+F1ypnzUTh7LgLXroXizu0g+F0OpTiWRzlfFM6cjc"
                + "DVqyEoL6/bY5fKinDjZhSu+sfj6s04XJML7d+6E427d0MQF5+sMmPoXaDeBHTzOI62pO1UCGjGTL"
                + "A9bt99LqZ6V6iNOmzC6QOgZB+KUleiIMkZhUmukKV7Q5ZzEqWyMFRWlAhJGxBLrNch5uKnwlQqIl"
                + "wlaT05AcuefILsO19inVNXOC2wgMvCrnBdREKhi3LIpQucF1nA06oL+nWbhmbf2WLepBHwtOkC1w"
                + "WWYtouPP0KK0tYmv+ALbuUhzBV4eZ+HBNG98RKO3O4W1nAzaor3JdYYqWNOexnD4Ce/gI8e/buF6"
                + "qqNwHnL/wV7bRqElDfyBHPg6LEVAqUlZUhJTUTyclZSOGSrRSK2ylCmETOSEFBIWRFxbh3L4QIHc"
                + "zl1p1geoqDcOdeMIqKVckjKSjAzZuB8L/5HP63gnHzTjhJNEkc8mUCRZVpKmxXoqy0lOqRUXlFJM"
                + "UoKqKQ9svLy2uYo8JCKUKCYxBKDlRYdQklCYvHtJneuH2gNRKvf4Uov2aIp3Zl0cM/o5gIKCMpfv"
                + "QJNaCbAmF06V9F4pvAeeEYdO6zB4t+GQIkfSTMNVROE9sEj0+2QIfOdkhMVrxqIMede4kYO/gHFD"
                + "/7uyJvKBM6j5SPsM62EzyXXxRTv1vQWdQPEyf5QktnqQoBjUxcYNbeGXGxyn1Wwk1MTU1Dt+6sm8"
                + "YFnTq7opM5iTwUtzuas2PO0NaxwradfsjOzYW2vhUfS9bSsefSpp0NTDs4IiNLeK9XjrDweGhqLO"
                + "Zp2VAfG2nR0GCyBCGh8WIqVRQXlWLCxI3o2dcDAwatxMBBXly6d3ODo/NBMZUCT59FQEtvMfToId"
                + "M3WVqrGLZ3hXlnW/pNtjA0dcCPIyah6NE/UPpI2SQr5v2x8d465RERhDSp1S+jcfZSNLr2WIHjm0"
                + "348FxRQFOeRl4OIj6Cy/wumDnnmHi2wiNXQlZ51Ggf3NjTGghpSvnYEB+VHUB5gprg0cnv0H+gA/"
                + "Lya5+N/rZRLwKyN91G/LAOOnouKgQ0NHaBeVd3pKVliikZBAKmpKWiQ0c3UvWulI51OrP0Yli1vY"
                + "zEFS3VbLFpy2XkSfJh2tGJjrmSdl1G4koa1hmWPTyQla14q40hMjIZJiZOQjeQmTsfnTGmbSMTB4"
                + "SGKdaGUcZTMjka2g70O6hcfRcYkLBQU9MJXS3dkStR9dADg6KhbehA50Nlm6qKERcPqtuF6vSk37"
                + "EC7fS8MXzITCLgX1D6uGab8KXy6CNUBjfF3CnDEBqRg6OnnqFD+0kouP8lyrlp/4SnY5q19Bm1bR"
                + "/8E727TMLlK4qVIDZsvAur6ZacoPK0nPh0Pgj9lB6QIfjtxLtuMilQLwIWl0kxcNgqIpMqAfUNXd"
                + "Cztxfy85VfuZQTMA2dujCi1T1dn3ViG3dwQ2tqW271ZQSUwKwTI5U8jysM6iBgVEQyTE0pramifB"
                + "MioDFppVDSjrVh+67r5EipevK8T5OIrqVtS2Y/SEwp4FlwFGm/pTCp1vWkKoo+UR2DFRg5bDrd7D"
                + "/Xk4BNiYAfY/bUwbj3SHiIho74FS5zetETR2adtJmgAYlYjz4mzfgxzm7SRu++q8Fmf8fF52JQv5"
                + "lIuf0lOUWk/URtyUiIiI9xZJU+xk/Yyst9X6gXAQtlBeg7cCWfcKB88fUMnMmUrebtKQXqQ0Dbd0"
                + "DASsyYtROaWtXnMTICufIZPqt9TlelZWAE1DWS930qiKYsZqSpzZgG7rAM2gZeGDl0hkhARoBqBH"
                + "uZiAScM2Uw7gQIBHz6PAnaegvw+IQmn6wgaDRBs5U8/BOR8P9h+rgBWLXuFuYtPoB9XjpENsH0yt"
                + "OVPf0Imff+hb49p+Fp0IsHDN426kXAvLxc9OwlHwVRXHxGwP4DvXijXoH6EtAPufl5aM9M8FsgYH"
                + "ZONiy6s9dDa58Qoa27FOPGrydHhHXwCL/hydNwtGo9n8hpA3UNG3owXPm0M+V8zPRraK8kB20Fmq"
                + "utRf8+s1H0+FOUvIEGnEsEvPcwkZ8Dg53TWQztPwKlIWTaq7UtK583QcKV5jDQs8GkceNREUx1K6"
                + "Xhbb+IT+DwSxc4ul4SSxR+3/tAvQgoobZZ7z4rqxHQHQZkgnv0dieCKpPj9Qmopm4H362XkZuXjw"
                + "4d3o4GZN4yc1RY36U8vbIIpHdCbAIbk2aoREZGJg7+5o+jR+/g6LHbGDR0NfQM5VPRlpFm9EAHc0"
                + "d42g3GRpdeWOXQH3u9zanR/1fuUCiIIphMJspxNUQk4LxqBMyjJo5R5+XYu4ockjDBpLL0zNMuCm"
                + "DTvf4He9d1woMTaqgMkpteQfsxp+bhsdbo3WsZcnLljscHRkCZrBD9B9DFN1BtAxoRuTqZL0NKir"
                + "Jary8BrxAB89DxLRHQ2/sM2pAmk6etLsKoji2OHpe/6M46aFU7aSfN2AIN8syFPMugb7wCFpZWkA"
                + "R8AUTTpQ0nLzaEOQqCs8BJQo5F1SuXTJRfw6wu7HhMEyycPgh3A5LEWoXrefTYYxgazEDKzRaoIA"
                + "dETrKigD+h8OGfqb3ATO3HKKwiOXM8PkJFyGcYNXgY9h95zMt536Bf+PooKZVi0BBv7jEq3zTWNj"
                + "I0dkZIqPJkUgUBO5q7EYGYN8tMlSDK+VWdEIGAzAQ3NAHLK4vJvG4kM8vaf3U/EOoaDrCy2SfmYl"
                + "DVFEJXlBM/b1aOgYkHec/WSPb/ig+7yUknF9YHKPv9cwQcbg6/bV/j8rZvcHk7hXWI346v4L/nKw"
                + "zt9z3uP6j+EFVgxNjdpB0HENFZP6NCEzLCl8gJKUohmwgb0QR7vQwwcuw2yv/+tJ4y6kXASpRi9J"
                + "gN0NWtPhOa2j9atrisMhFV+KHJqWkwNnMk79IeuvoOpD0dyYt2UCFhbQQ0ewsEjE9KJdPuTOWyB0"
                + "hIzzQe6+6R52WiR02M/v2pTSstFHOqYuKkLbUSMMn/61oI+BHKAj+i9tmXmPyjI3bvuYk9+/yxm+"
                + "TXOuUGft1zHXv3+iMzi/1eVdKEhiZDQ9cBVw9qcW0pdzRqk5JnTZF5+9/oYTETj57KmxXvH6/5To"
                + "j8AlRi6rSt1IZSJaAJEZB1FK/fUHMyaVGxFE8eR+HxwwgKI/H0aRTu3H6ObkQm+UoKqgS8jBwioG"
                + "nHpQ1OwNPn7vNZ1/I6GflYvb17e/IuGFYPz08Ph66uHQICQsScqng9AjZFSWBTRF74DEvt14olvD"
                + "lcPP3Qy3I8pE+/QFmtjs5HKCFiIuJ/YDW9G+wcT4k5P0gNyE5aOHE7+/10Ex34jaq64XQTNLSdMG"
                + "WKL0+jipo/uKK8BL36ePH+QzkZlAmYm886ohuegPZOR9BGU2i7sTqNiGidu7pjz54raE91sA5voQ"
                + "x3tG1rg82bLog5VX9DfQgYdfEz2FmvEkt4c0gKZehg6YMNzhZkiv8k1qUwvzLaZu3JO4c00L2/O1"
                + "232rX5+0K9TDDD1q0XoV6jE9cV+mwlrA4OSEp4+eRSSX4heip509UJ+DY0YEmxDIMGe1MzQNF+1d"
                + "F3xohRPmTyc9Gvv6p3z4Ybf568kXLWfIBen4BNEHXh77CzWi2W0DDwuxoKY4P5iPJrRQ4JG6JT1C"
                + "vljsefMWbIQKzfck/M8d+DehPwkt8DaGhac/Mlv1nsJrAOWDUi0PpNcjNc88bJkV8XAckLVmhA1l"
                + "ZrOAIGBcfAwNCRD5/Jz7mNhh2Wuh7mx9kkCw0NhXNiSO3ETp3ckJIqfNJVGe+fgMK1ffQoEYP6T0"
                + "P67c9R+kS1XinzuoM/xk5PU4z4YTtKy17t9dJ3hXoTMDQ0hhrtDmS+FA15+Q3VN3JFZ0tPZGSqkq"
                + "Q6pNJi9OrnRYSog4B5REAi9JsSkE1UkGP33hvii1RyArrz9uC5S8JbePt+8yfNzt5bFn6LCZ2Puo"
                + "Y9ztbyll69CEgm2N6moWZEV6K8vBKjf9iC874afJaLap1MyCOmtmF5yN8wauBwrPsv04L1JqBMVo"
                + "Q+/VZB20hhyqqESNNG0wHz5rHPXNW9pGNyWiY6mrvy/kOWrzoBmQk269iQBKzE7Lm7yFOXD7+5wt"
                + "DIHRZd3ai8LJ4iMiaRzscZJmI5TKO3JQ3ptLTm7JjXIyB5wU+bIoII6Gj/5m1A+TvKO3c/xswf+p"
                + "JG+H81OruVhXnJj062g0lHO8TEqc4kep+oNwEZ5pG5aqvpiPbV+vPYjTXq6MKX4nB1O8wHxquDzd"
                + "CdOWsb3UDmyAjaiBOQhJnwrXwkhNqAHaisBiJgPmnUHj2Xk4aWPzQu0CECTZy0GRKJhI8wsO6O74"
                + "eu4cOK8jq1DZdSu9ELRSWqU5Zel4AVT8gLvvwPTJq0HHfupeL2vXjcvBv3EonHvd+j6fcqVvWXIy"
                + "VNQk6cDUIvfIvK55+gOEDR4a1wRD6BjMxwUcD/8AkMznMtMWnKAbGE9483IuBvv93ir1YKN6CakO"
                + "ZgZGJLrrEbfNnvKWLjkxEZHY/jJ+9h+OjVpIlUl+hQIaDcBHdsOAKy2S062nZ8upaQhnWKL4N5l+"
                + "Xo2WsFuvf0RK/eK9C5C+VVWuuGzX7RN7THs2oTbV+PgB/zCQk5Af+Er1s3rLTugVV2FvCy71q32F"
                + "liPXm3IwYNw5FjNZsAC61PwcvajIjVlHu7fNLrI9buE0ZBZHwChIKITAPnPvgXunecipMX3t8ULG"
                + "W8EQETE5Pp4jvD+AXDa6btPegmOUJT2xZmHZzoxi6FOhFPi3VCk3ZTTisnYOt2Nti18yoRUEIEZP"
                + "PrVAnYjbQYm6yqjOioFCIWOSwvIODqdWf5MJ/8uFyMKY+BIRN3Kt+1xnQr9pCw7phdO67zcuSN/9"
                + "clIB+pYDOiQ8gxYMtu8FnJL5HEJtjkZoy9B1XXhrn1eywG952IgodfoEx0PBgBpYyIgX9C+s1mkD"
                + "35lOIV5yFj/YHhTXBqoz7Mu3miQKI8a+n9gH7hm6ASCxbtIS1H5KIboHzTqgvrI2STRY0Zmao0UG"
                + "1CN1tjCa74PSKSMRNczQum9pm55TJkk3lWRmR0Eh1byskkL8uE8hmbOCEiXBhHHTdxCzR15YR5kd"
                + "Q8PzZti61po4zXJSAngRgqd5XUJWx4TbFCqmJtmNLyCgwb7YOzvlqC4yGaXlYmm6iafvsrDOr9I5"
                + "6yKVtB7DwU/YJ8lnXYnzFh+BC4uMoX3Ky7p+Jt4w0JCAQ+i4KOri1pnlqckXoId0g6LKW2WCZSU7"
                + "MErUbEFY6TdqJ2G5uav32Hn3gGAjw9j0CDmVcl8nACGjshMSGVOzQdOjOSLOMaTZ6GCZu/Z8rasX"
                + "Lh8aoPFBum62yxjM5LoXnrQ8BXl7qXZ9u28wGmju1HGvIvPA1LK+QhckV+BE/rfmhnsAUzxvyEyh"
                + "BKozJCIkzZirjQCqYm8xAYKJ/k8H7wxgRkcHU5gtZtrF9Bs7xY2Nw6tba2WGwrfCRaWihD776e0B"
                + "NHSuTCJj0YGDhiwYJf+bDftFlboK1vxxdFUiaOgaEzevXyQHlZKfyuPIK6Jpv9okRQKkedtHdbdW"
                + "uoszl+TPg6hlbQ0bMX5/q50++itiKRv622Na5efcrPjeHdElDQgKmpEvTpaYvQ8y0AIlLVBAQ+Da"
                + "sp7h5Uw6AhqxAVlYMu3byw14dN2RLMLyMfbyeycskh8ba2wOARW6s86veBBiGgpECC4SNW0s1j7T"
                + "w5CVQ1SN0i3uQObtDVX4r2He3JWVHMfXN0OchJWWM9QVN3tNNyAnsRXpPamGwcWplcpnQebdvawd"
                + "5B8PhcnA/R+SnWMWQk7tBxGVZ4HcWmTWewfv1pIjPJ+rO0fw5TpvlCV8+Jp+UEpPYqf+neQ/7SDx"
                + "Fw8jsk4GFBA85fQo6HFXsx6X9UJh+UkmktDfobRn4/AsfOCB//OXH2KVmPGUi/1Zy/k8zIKhDwY5"
                + "SQQ1L45B/oYTEWO/bKvyzw7onYIARkSExOxcCBHmjThplBRr5XNcnMJLqRGXeFtq6N0kwaoesmIj"
                + "KOzJ8979yuPX9t4g59Mpl6BrYIJweEvWw9bOhqPuQmT6NDbcExo+ruEA54GE4m3YZPSBDyuBAhXT"
                + "BixGpUlAujCW9fAwpOwxpHY5w5H4bnQdno34c5Hp/VGPFg6bZ56GLCtN383AQyVdJDcgALJw0gjc"
                + "fKU+onZA4JtR8v7dLnvy0948WDBm8LDUZAhsyMXMz4ZStaqy+mxj55x8wkkmYTCFlTIzLNYmS0DK"
                + "3UrcixcMHVG8qLMAoXkGHfgWto0XYBEXE55fGgvNW0HRd5+e701HuglZoVfHcIkwhCI+JqOChsHH"
                + "u5+xF+XFGXos48SZ742oE8D2lB9uabEXnVEcL7GbVpQAsLRsDa5wO+ngjtOkasdS7G+O1wCCZN3Y"
                + "EzW8nxIO9YFqBwLMqfNUHSzS/Rs/tchETKiST8jvj4HL6Gz8195JCwfGIeoduGzHb4p5gxvi9mzT"
                + "vO079rNCgBBZTzD9YMH7kO2joOZAZtoaG1lL/CyWZQ65IW0ibtw74b14aI16HTUtg57kOyyizqmv"
                + "DddgE6mrbQ1CZzW2M9arZPGpfi2eQBdSp37ZozYk729aabaNXWmjxi9pqmIGw47oI4/FYXFizcRW"
                + "belspVEF6trT32HRTWi64+IVXfxB0WlkuQF/AlEEWXlr0A/qaS3AS/re8Ey357sHDmUCLM3yB7xM"
                + "gpJyA5HhF/wvyp3bByVe1L4m32vUemdhyKg/5Z87xi2OucX6GT+Uxcvyn/RotA3ncBOoO3g4qKYv"
                + "x+P5SIcBYzZu7AiJFrMHDQSgwe4o2x49fDynY/fjt4A0lJNafv14XbtwIxYrQ31LWsoUaOAhtSY+"
                + "0/DU0n3iHejkzmkJGrcNFP+fP6lVgwezt/Sd2YPGr23TpD8ow7d3FEci2fg1DGvoPX8fW3vxAJHd"
                + "GW6lHXscPXLeZjMp8dU4GRP6zFN98tQVtNephIWrVzhLbeQvjYd8Ku5ZrY7qb9CqIjSs34bRTuX6"
                + "WJeVN/hpa+FSKvfqfqeJAwEl3fp4YBA91RKK19bZgyajJ8P2IDxg3pg1U2JnBf3AEeorgvbk8m3g"
                + "SW1BY06+qBTP4h7w+egOwHqA6/VVSUoqi4AKWlUtqrfqFe5QcL5ZUTsdkSHT5rTmL+nB2YQc7C3H"
                + "k74bXqDG7cei5+E0QB1v6Li0kmrzAFUdFJXCKjEsk0pdAxxfdDaqCyEjnZubh0+TGuXHvG5fL1Z7"
                + "h07SnV/xyV5SUUPsOJk/dx5sxDkkc4e+YxTp99Qg7DU+w7FEieK22/oRw9EYguvbdgg3NP0nTkRC"
                + "iZXvaucfGzzzGs/zicvaT4IGNtiIlJwaatd7F5VwB8dz1UlZ2PqJnzO7btvYTo2ET2098Z3poGbE"
                + "TDICAwFSMGTSHH4+8oe6wgXxF/vbIJtiw1wPRZyu+tfFh4IwKmpeciOiaNJB2xJAWFbEWESj5wzt"
                + "ZukS/uU15egbS0bEgKFLNxC4uLyXPOQJFM0FgFBTK+OFE6lZmZwcIcypND8VJkZir20yhMTc3hs3"
                + "GUUVgoQyrVkZaex8tgaTOz8pGXX0DnlqqSni1IFBubjLy8Av7h7PR0RfkZmUJHc1lZKY9jZaWnCe"
                + "Xl5AoTAsrKSig+i+JZXXniOWWhtETwjhPiMxAZmUKSSponDcVFioWUUlKyEBOdRvHpPMzMlI/o1F"
                + "Q7JaVlGDBkNc5v1uF9fOyNN0Y+ZoIryfGIu/INuvdahNj/otktr4t6fiekEuuobadvYA8DQ1u0J0"
                + "dC19AeXSwcERObgIWLdlObbwWlE25IBt1gM1MrbNh4lu8zczpn3i5812oxJk8VFjPff+AytaUWok"
                + "NnF3S1YN8XcYCZmQ32UHy/AZ4w7+xGDXx3WJK3bGxojfPn5Z9lFW7cvv3XyMGxhXl3V1h0c6Y2ng"
                + "sGD3anG52AHyd4o0sXV6SnZlAToBhDh3tTmS5ISk7FtBmbyDlxRPv2zuRsOMHQyB4rvY4jIiIWXc"
                + "yd0b4jnYvpUjp/cn4o9N3mh4joeJh2dkaXrm7o3o3Ol87JtL0VHgSEYMWq4zAytcOgwavx/WBvmN"
                + "HvGDJ4JST5+Vi3/ix/x0SHrpmhmT10DWyoPgcytcx5qEnA9ZtuYdKogdTO+7PKVCsZG04L/Qt++a"
                + "k3fDa92gd0XgdMcdQlDY16aUCmydhE1FGjN+PZsxi68BHYse8aevRyx927QZizYDfdYNbHJhCQaR"
                + "EtTSusXi14pv7XA9GO9hex90o0rflk0OycfAQ8jMZqn7PksS7G4aN38ORJDB49jiRP0x6LFu3F0W"
                + "P3cejIbWqv+JPzwmYoswsiXJSdu66iVevF2LTlAqW7Sw7OLZw+zSZfViApJQO9eq/EyOE+mDh9Iw"
                + "yJFCGhyTxfP9Iww8hBYm3ESBI2X9DAxAHX/AO54+Kz9hQdS0V4eAqGDvXhH+IOeBKJ1nT+tksP4N"
                + "ixOzh0+A6d7y3ShGlEyOXo0cMTp04/wKVLT7CWSGdn/ytSUlNhbu6OIcO88fhpDELCknH/QQRMzV"
                + "0wbIzyFwWEMD4xF93Io44+L7z3K6wDIwhzPC7u1MD3VFYR17q1k6W+UlFRUac0NOpFwOycPOgZO2"
                + "DlCkGjzVu4HePHb0T3Hstx+dojzF24G4PpZslRSOZPR88WG9YJa9ANGbIKvfsupxsbi++H+KB7n5"
                + "XkPAiOyblzD9BSbSGf3cKQEJfBCdF3oDcmTt6KcRM2YsKkDWQS5e+cCBdl957rfP7hmLE+mPDzRo"
                + "wc4wMXt9/oiGD+jp2+g6++mo3Pv5xNpJIvSQEM/2EdLHp4YMXyU/Ak6dbbA50s3XD391Dylpfip5"
                + "82w9nlCJY6H0Jnc1e4e5xABDkxbKhu8BAvTJy0CWN+3IgZv2wi0y7F9p2XMHCwFwaSlh1AGrBn7+"
                + "UYPsoHgc+j0dF8GZxdFSMpDGPo9wwZvo62VAk4c+5heFl1Bn/RSBxGY+RjM19kTz/DkH4/4sYN4f"
                + "3rioryGkR5W8II2pConwYsLOSLBvXr54PzFwOwY/clLFqyB1/8aw55hb9joc1eaOs74szZ33HD/z"
                + "ndlKv45pu52L3rGi75PUaz5vPpZrCuEAd06uKMr79djO27hJkZR4/cwn++nYenpFkZGBHV1K359+"
                + "NOHL+Lw4fuYN++m+TNJtK9YhdDuCDbd1xD8xaLsXHLOdKApJUO3ibtdIsuWjH8b5PG1ViMObN38q"
                + "G9lq3n4wRpU4bBpP0MTFwwdeoWTJyyCZNnbME5v4cIj0xAG83FRCIfzKJ802fuIBPvBHvHwwiPSO"
                + "BfebdzOMDrOkIakHUpxcbGw9p2D5bY7OGLZN64GQL3FSfwv/+Zj5On7qFP/xV8vuHZ8wG4ei0Qx+"
                + "j3sLUGR9HDqyAg6JqFYfjA0agM+ovQVydfRYFJ7MfwsTHFrAVHeVrm5b9LAjJpSNSLgOxi7dpzGZ"
                + "2IhMZmtry9ZU7todGj1iGZ2lW37gXzTziYUDuHzWwxNnWgNqE3Ap9G4GfSXtN+2YzImBREkSPAug"
                + "d+oRs8fNRyFJdIcflSADp2tkeQuKYf6y4Z8L0XlbccPfu4kyyndpUdjp1UXYf60PEb6GLpiD593X"
                + "ja7j3dMXSYJ5EggDTSckwkrVhUxJygcixevBuWXewREPAMM2dvxyKrPUIhSggLj0Onzra4eOl3MQ"
                + "awtz+IKZM3Izg0Fv0GuQl1kcZk59TZ3BG3/Z/Ay/sEjNs7UFt1GbqRRbCwcMePP63ms2j27fdHJz"
                + "K5hiZ2dF0cKLSFZU8XIjxrzwoEzM2VwNzCDdYzuuP63ma4sKkVzvm2IGmOS9u+w28r1dGrxyIkpg"
                + "gOUWXFi03m25CGRD0JKEAqk5E5liAnh7xJ8iiVn+Ly8jK6mIXCcbqo7Bvl7Hjtn2Wo5N4uW8a3vK"
                + "IMsqIiCoV3SdjTzZbjLSavuZiHJUSkYp5WgUqUkglnS+zy42K6kpJSSCQFJKzvURX51I7NlxSSp1"
                + "mKYhLFmTPQ2dJ5sPLYUr3C76I6WNqSEt5cEOoqrqqLbVfwtGQhqM7s7HzkZEu4ly1vJjBUXbNcSk"
                + "NhCZUnQDiDsPBYrFhxBOvXn8Iq75Pw9j6tkNWnqFlxGFevK2YzV3AN+G6lIfFGBGzEq0OV4HXglR"
                + "KpojaC1C4NpynrA3JvxC1VNBKwgSD3HrknSf/K6XozfciGwZiuZtul7Dj7x9K8UFh5qlJBfwQSye"
                + "sR6lImRl1STnkUelzQx+W0VVvalwmrs3aw8yYLRlvcL1dKx/aZi8l+Q3W8JQLWdZIC2MWTo+4f9K"
                + "qoT35Fnpr11+98qt8g9jfj9/tIOHcBCXv2IOXCeZQREdgRluZlQn9qxClEOO/ahP5UywvkJCYjyc"
                + "YWaXv2IenqZWT4bkFpkVRMKqThws9NPHsez3c4GPHFg9XA4nhOTuzEdWuRvHO3WJ5A9tSVq5AbIO"
                + "+3VUW9CVhWXgxbhz0YMdoHJ07dFWMFhIbGY9gPXpg2ayumztiKyVM24Mhxf4z7yQfnLj6gk6vAUt"
                + "d9mDt/F19rUI4TZ+9j/MRN+HHiZnIOduHsOaFxfv9OMCZM2oQJkzfh+6EeuP0gkPcPjhm7CT17u2"
                + "Lj1vPkFF0hR8YbM+dtp/j18Fp1Ar/uv4rxE3yQnJCMjRvOYdyPqxEUJHy82mftGUyZsRmFdCPYZx"
                + "m8ab/PgJV86eH1G0+Jq7zWdsFrBydgOdNwQq7Mg/sRu2w5v3Gphw4h6ahq90tdeJUaX/2shLQFMd"
                + "GI+/hPkNy5A1liHOKbNIUsIkKpZVoT8joKqM0qIceyOqqfAyuLjRBF9bZAwd3bVXESf39E/+NzFE"
                + "aH13re9SbgvfshaNFmCZqrWWHQ8NXUWFcMN8XGpmLOwh3QMXDkLxE5ux7B9t1X8I8vZmLzVmGO3o"
                + "ABq2Bg4oRCPnwnYP2Gi/ji3/Mxc84ujCCP+j/N5uHoiVu4dPkR/vHlbEyevh2rqCH+6EkoLHu7ob"
                + "35cmzafIF3OF+4+Jg87bX4tuUS8k69yOO8Cmv7A/jy67mIiEzArFm78M9/LyDCuiGXnI+Zc37ls2"
                + "pycnOxcOEuNGs+F04uB8nTPYBvv57DPWPmAL0qqjQgbZdQvqRmzZD901gyPWWQFsjABgILk+OR8f"
                + "QhZOlpPF1eRBiynj9H1u17yPG7jPxbN1BMzlXOvXsoDA2CNCEBWdeuIt3vAgpjo1FSIEH6g/vIev"
                + "KIm1U5SnJzkHvuDCSURpYQh8zrl/lrCAwsVdZiK6SOGo1iag4kTZ6CRMtuyLrqRw6UDAXhEci7dw"
                + "vFebnIOX8OWcFBVOd1FGfnQZqagsjx45B86CCyr1+BLDkJ0uxMZPldonMhJ+7BQ6TdvgVZnPBQZ+"
                + "7cifgZM7kZZmB1p8yajuxtW/i24owVqAcBhWIWWR/gn9bft/cq1NouxP0H8rlkCvTo7YnBQ9fw7a"
                + "vXg/C/RIbdpKkYRvywEV26LoO0UKEBfbf6cdIFBbMfVAkjUyeM/2kTacIAqLezw4jh3pg6bTOehU"
                + "bCzu4AX6d55LiN2H9YeF0y4EEE1fELHxVhsHfcjxbqSxARnYx5i/ZA38QVWjrWmEFkZAuUW3Zzx6"
                + "07QWitvgjzF8hnEoO//daW4hLiX/0bvQIBhUvP/mZ6eCKzSRMkD+oPWVkRktyWIXb7DsSu8Uaimx"
                + "uZw71IOngYcXb2iPNZh/g58xE/YSJvK0X37IGcaxeRsswNcT//jNRrlxHv4YpYL2/kRIQiydYeqZ"
                + "s2V2kwSWgIIrU1IU1KRNLhg0i5dJ4eAuGoLDEJCV/9B0lEgrg9+5G8ZiXybt9ETO++KCY7mWhjjU"
                + "RPN+QTkcL1tJGbmoiYsT8g49JZpK5dg8SpU+jBSUJ4e1NIoiKRe+UCIuk3FaakIPnXPUjdsQdx02"
                + "bSeVcguncfSENCq4gmjSbNa2tLt7I26gmolwbMzMqGWSc3dLZ0x+z5O9CshQ3snFRNDFspv3svTw"
                + "waLBDw8pVAfPm/c7HZ9zzf7zfIB52IgMqLP/puvYSvms0nMocgPj4RGpoOfC4h026t1Gzwy9zd2L"
                + "D+DJIS0xAWGoNbd59izI/rKc9s/hX2+7+HEYHnUB2ClrVfuh8t1RcjMjoRs+f+Csse7jhx4ja0tW"
                + "15p3Lffl54/DgcunoOZKpZZ3AxSsuKMXTEGujp2yIjXTFNvZxuqLzLhP22MnHxcnlcJR1nMVLS6G"
                + "ziA2t45y11RvIXXyBxqR0Sfp6EzKBQZN0LQGFUFKKJWFk3byHWzhHFxVIk/jSezFQMCp89QcLEH1"
                + "FSVITY/n1QnJEGaVQYYnp1hyxFGD5M6t8PmTu2VmmaItJEKWPGI/XpM2QRARhK6aYzCqZOmowUqk"
                + "twhFhLjbTSokXIPnGCzlOK2MHfo6S8FAlzFyD7pj+KMrOROIa0ZVYqYvv2QxFZiJxTp5G8ZDEvL2"
                + "HKZOTduo7cyEheV+zIESh48hhZe3Yhee4cXr6wsDu1B2/fRW54WBUha0O9COi75RI++9d0zFu4G2"
                + "7LD6P3gBVQ01jAP7ElB+urMzZzgEV3V76fkJAKE1O2MqoDhoz0wtffLcAv83x5e1COtUSuf345E1"
                + "27sQ/GWMHEyB6Pn0YQAe+TCZ4Fyz6e+GGsN44cuY7ufVypTeiLXr3c0dHcHuz7xVevB+Ljv/xE7T"
                + "nh8wrMtP79yxmIiIjB5Emb0a7dQlJVMuw/cA1ffzMX+oZ2KCjIxTbfS2jZci61Ad3Rrc9ytGg9Hz"
                + "t2KobrGDw8D6Frd0c8DY7ikw82UVNi/6FbMDZahJgY4SWqUrqhkXY2KJHKUEZtp5hx45CxbRtiO5"
                + "qhwO8i126F0mJkbN+O6HlzkHxgH2mqSmRs2oDkRfNRWlqG6H4DUPDwDjKcHZHm4shvXgZpkfS5c/"
                + "l20uYtiJ86DUVEGrliKTh+AuHm5kiPjKq62eyqSm5cQeLfPkNBXExVfCGZ8ITvv+f7CQsWIH3deu"
                + "Rf90fi8BE8LnH6FOQdPYwcr1XIcHBAAd3HhN7dIE2MQebxY0giLZ3luxXxi+cjxccbyfSwFOVmI6"
                + "ZrFxSJppidV/alC9TuPcIfEqFhUjtem4DMM7p4IYC0Fan5YmGtlMDAcKxbe4JuhPCEMpSWFuHggR"
                + "s4evQmud+ClmCmlc0Wsbbbj53UJszLV13v5PGjCGzbeg5bt13EwUM3SasJJjAsPIbH+W6/gjXrzi"
                + "DoeST87z7nIxPu9ACERwsrH7D6N2w4iYcP6akjYvvfeIotm8/y6V1+fg+xe7cfiumCMs119Ngt/E"
                + "rNAdahzHD79nM4OR+Bk+thpeaE/MJV0kPwACu8jyE1PQPLvY7hxq1nVE8kljlT+yg7h1+XAjJLsa"
                + "5OSDp5GrFbdyH1/GleQuratYgiYibduoMkvyuI+mEUUt2cEGNkAElIEN3Yo0igm5lw6hwSZs5ABn"
                + "mMMTOmIeNhANdc0uBgxG3fhfhrN5B44iRv/zGCCTqYiPTrfmSeOUlbAvFY8zCDtGySFbX9pkxCDp"
                + "lQ1i3EkH/3LuIcSCOTdoqdNAUZ/teRQM2A9IOHkEmmPHbyROTGxiLZ2QXJe3chJzoW8UuskXz7Nq"
                + "IozDx2FMnk1SYe3of4dasRv3oVEnb6ItHWhky6UAc7h2jSspn7D4hXUDxQC+pFQFXIDYEyWBqhES"
                + "xAfqnqA4WGfB3IzYAA5bprO4+X1aF8vLa0bJm0MrrJghkuyMmGlMwqS8mefn41yEQXFZJW3L2bbm"
                + "oUMp8EItpuCUoys/iVKiLtV1pWzrdL2IgQhYx8ZXS9Wf4S+stMOytT/guy/Kktd+gIkoPDqupi/1"
                + "l/Wyl5pCw/e/RLi8v4kB0DK7+YlVVSLByj8xLKp3hqq/JzoTiWl60lyOLZuZRQ3Tw92yYpJg3MHl"
                + "1WFmv/cRMvjgSxh59tManJF1XUywQrIC+8tkpYXO2Vv/iU6joqj6+7XAG1paueXnlfvi3c2uopVV"
                + "DjYPVyFPucppw8Qhz7yxwVSVIScoKeIycgQHHD+F8B8u2qONpQPi4Hi8u544+8gPvimaumk+8Lcc"
                + "pbwhkJe6pQTlX1Vymh/AopRVWD6pG60ynwhgRsxOuAaYNXuSmvAlYOE06Khir0PaCRgO8YqhRsGO"
                + "Z8wPxrJGAj3i/q+U5IIxrRMPg/pQEbH7Y3Q8NfP+D/A4pZBgq07uL4AAAAAElFTkSuQmCC";
    }

}
