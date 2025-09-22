package ta8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class SubKriteriaAhp {
    protected static int nBanyak4x4 = 4, nBanyak3x3 = 3;
    protected static double RI[] = {0.0, 0.0, 0.58, 0.90, 1.12, 1.24, 1.32, 1.41, 1.45, 1.49, 1.51, 1.48, 1.56, 1.57, 1.59};
    static double[][] matriksBerpasangan4x4 = new double[nBanyak4x4][nBanyak4x4];
    static double[] jumlahMatriksBerpasangan4x4 = new double[nBanyak4x4];
    static double[][] matriksBerpasangan3x3 = new double[nBanyak3x3][nBanyak3x3];
    static double[] jumlahMatriksBerpasangan3x3 = new double[nBanyak3x3];
    static double[][] matriksNormalisasi4x4 = new double[nBanyak4x4][nBanyak4x4];
    static double[] jumlahMatriksNormalisasi4x4 = new double[nBanyak4x4];
    public static double[] prioritas4x4 = new double[nBanyak4x4];
    public static double[] prioritasSub4x4 = new double[nBanyak4x4];
    static double[][] matriksNormalisasi3x3 = new double[nBanyak3x3][nBanyak3x3];
    static double[] jumlahMatriksNormalisasi3x3 = new double[nBanyak3x3];
    public static double[] prioritas3x3 = new double[nBanyak3x3];
    public static double[] prioritasSub3x3 = new double[nBanyak3x3];
    static double[][] matriksPenjumlahan4x4 = new double[nBanyak4x4][nBanyak4x4];
    static double[] jumlahMatriksPenjumlahan4x4 = new double[nBanyak4x4];
    static double[][] matriksPenjumlahan3x3 = new double[nBanyak3x3][nBanyak3x3];
    static double[] jumlahMatriksPenjumlahan3x3 = new double[nBanyak3x3];
    static double[] jumlahCekKonsistensi4x4 = new double[nBanyak4x4];
    static double[] jumlahCekKonsistensi3x3 = new double[nBanyak3x3];

    static DecimalFormat df = new DecimalFormat("0.00");
    public static double roundToTwoDecimals(double value) {
        return new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    static double IR;
    static double getIR(int nBanyak) {
        if (nBanyak < RI.length) {
            IR = RI[nBanyak];
        } else {
            IR = 1.59;
        }
        return IR;
    }

    public static void setNilaiKriteria4x4() {
        double matriks[][] = {
            {1, 3.0, 5.0, 9.0},
            {1.0 / 3.0, 1, 3.0, 5.0},
            {1.0 / 5.0, 1.0 / 3.0, 1, 3.0},
            {1.0 / 9.0, 1.0 / 5.0, 1.0 / 3.0, 1}
        };
        for (int row = 0; row < nBanyak4x4; row++) {
            for (int col = 0; col < nBanyak4x4; col++) {
                matriksBerpasangan4x4[row][col] = matriks[row][col];
            }
        }
    }
    public static void MatriksBerpasangan4x4() {
        setNilaiKriteria4x4();
        for (int row = 0; row < nBanyak4x4; row++) {
            for (int col = 0; col < nBanyak4x4; col++) {
                jumlahMatriksBerpasangan4x4[col] += matriksBerpasangan4x4[row][col];
            }
        }
    }
    public static void MatriksNormalisasi4x4() {
        for (int row = 0; row < nBanyak4x4; row++) {
            for (int col = 0; col < nBanyak4x4; col++) {
                matriksNormalisasi4x4[row][col] = matriksBerpasangan4x4[row][col] / jumlahMatriksBerpasangan4x4[col];
                jumlahMatriksNormalisasi4x4[row] += matriksNormalisasi4x4[row][col];
            }
            prioritas4x4[row] = jumlahMatriksNormalisasi4x4[row] / nBanyak4x4;
        }
        double maxNum = prioritas4x4[0];
        for (double j : prioritas4x4) {
            if (j > maxNum) maxNum = j;
        }
        for (int i = 0; i < nBanyak4x4; i++) {
            prioritasSub4x4[i] = prioritas4x4[i] / maxNum;
        }
    }
    public static void MatriksPenjumlahan4x4() {
        for (int row = 0; row < nBanyak4x4; row++) {
            for (int col = 0; col < nBanyak4x4; col++) {
                matriksPenjumlahan4x4[row][col] = matriksBerpasangan4x4[row][col] * prioritas4x4[col];
                jumlahMatriksPenjumlahan4x4[row] += matriksPenjumlahan4x4[row][col];
            }
        }
    }
    public static String getCekKonsistensi4x4() {
        double totalJumlah = 0;
        for (int row = 0; row < nBanyak4x4; row++) {
            jumlahCekKonsistensi4x4[row] = jumlahMatriksPenjumlahan4x4[row] + prioritas4x4[row];
            totalJumlah += jumlahCekKonsistensi4x4[row];
        }
        double lamdaMaks = totalJumlah / nBanyak4x4;
        double CI = (lamdaMaks - nBanyak4x4) / (nBanyak4x4 - 1);
        double CR = CI / getIR(nBanyak4x4);
        return CR <= 0.1 ? "Konsisten" : "Tidak Konsisten";
    }

    public static void setNilaiKriteria3x3() {
        double matriks[][] = {
            {1, 3.0, 7.0},
            {1.0 / 3.0, 1, 3.0},
            {1.0 / 7.0, 1.0 / 3.0, 1}
        };
        for (int row = 0; row < nBanyak3x3; row++) {
            for (int col = 0; col < nBanyak3x3; col++) {
                matriksBerpasangan3x3[row][col] = matriks[row][col];
            }
        }
    }
    public static void MatriksBerpasangan3x3() {
        setNilaiKriteria3x3();
        for (int row = 0; row < nBanyak3x3; row++) {
            for (int col = 0; col < nBanyak3x3; col++) {
                jumlahMatriksBerpasangan3x3[col] += matriksBerpasangan3x3[row][col];
            }
        }
    }
    public static void MatriksNormalisasi3x3() {
        for (int row = 0; row < nBanyak3x3; row++) {
            for (int col = 0; col < nBanyak3x3; col++) {
                matriksNormalisasi3x3[row][col] = matriksBerpasangan3x3[row][col] / jumlahMatriksBerpasangan3x3[col];
                jumlahMatriksNormalisasi3x3[row] += matriksNormalisasi3x3[row][col];
            }
            prioritas3x3[row] = jumlahMatriksNormalisasi3x3[row] / nBanyak3x3;
        }

        double maxNum = prioritas3x3[0];
        for (double j : prioritas3x3) {
            if (j > maxNum) maxNum = j;
        }

        for (int i = 0; i < nBanyak3x3; i++) {
            prioritasSub3x3[i] = prioritas3x3[i] / maxNum;
        }
    }
    public static void MatriksPenjumlahan3x3() {
        for (int row = 0; row < nBanyak3x3; row++) {
            for (int col = 0; col < nBanyak3x3; col++) {
                matriksPenjumlahan3x3[row][col] = matriksBerpasangan3x3[row][col] * prioritas3x3[col];
                jumlahMatriksPenjumlahan3x3[row] += matriksPenjumlahan3x3[row][col];
            }
        }
    }
    public static String getCekKonsistensi3x3() {
        double totalJumlah = 0;
        for (int row = 0; row < nBanyak3x3; row++) {
            jumlahCekKonsistensi3x3[row] = jumlahMatriksPenjumlahan3x3[row] + prioritas3x3[row];
            totalJumlah += jumlahCekKonsistensi3x3[row];
        }
        double lamdaMaks = totalJumlah / nBanyak3x3;
        double CI = (lamdaMaks - nBanyak3x3) / (nBanyak3x3 - 1);
        double CR = CI / getIR(nBanyak3x3);
        return CR <= 0.1 ? "Konsisten" : "Tidak Konsisten";
    }

    public SubKriteriaAhp() {
        MatriksBerpasangan4x4();
        MatriksNormalisasi4x4();
        MatriksPenjumlahan4x4();
        MatriksBerpasangan3x3();
        MatriksNormalisasi3x3();
        MatriksPenjumlahan3x3();
    }
}
