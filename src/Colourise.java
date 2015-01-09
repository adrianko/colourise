import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Colourise {

    private static final String prefix = "\u001B[";
    private static final String suffix = "m";
    private static final String reset = "0";

    private static final Map<String, String> foreground;

    static {
        Map<String, String> ini = new HashMap<String, String>();
        ini.put("black", "0;30");
        ini.put("black_bold", "1;30");
        ini.put("red", "0;31");
        ini.put("red_bold", "1;31");
        ini.put("green", "0;32");
        ini.put("green_bold", "1;32");
        ini.put("yellow", "0;33");
        ini.put("yellow_bold", "1;33");
        ini.put("blue", "0;34");
        ini.put("blue_bold", "1;34");
        ini.put("purple", "0;35");
        ini.put("purple_bold", "1;35");
        ini.put("cyan", "0;36");
        ini.put("cyan_bold", "1;36");
        ini.put("grey", "0;37");
        ini.put("grey_bold", "1;37");
        foreground = Collections.unmodifiableMap(ini);
    }

    private static final Map<String, String> background;

    static {
        Map<String, String> ini = new HashMap<String, String>();
        ini.put("black", "40");
        ini.put("red", "41");
        ini.put("green", "42");
        ini.put("yellow", "43");
        ini.put("blue", "44");
        ini.put("magenta", "45");
        ini.put("cyan", "46");
        ini.put("grey", "47");
        background = Collections.unmodifiableMap(ini);
    }

    public static String fg(String s, String fg) {
        if (!foreground.containsKey(fg)) {
            return s;
        }
        return prefix + foreground.get(fg) + suffix + s + prefix + reset + suffix;


    }

    public static String bg(String s, String bg) {
        if (!background.containsKey(bg)) {
            return s;
        }

        return prefix + background.get(bg) + suffix + s + prefix + reset + suffix;
    }

    public static String all(String s, String fg, String bg) {
        String r = "";

        if (foreground.containsKey(fg)) {
            r += prefix + foreground.get(fg) + suffix;
        }

        if (background.containsKey(bg)) {
            r += prefix + background.get(bg) + suffix;
        }

        return r + s + prefix + reset + suffix;
    }

    public static void main(String[] args) {
        for (String c : foreground.keySet()) {
            System.out.println(c + ": " + Colourise.fg("string", c));
        }

        for (String c : background.keySet()) {
            System.out.println(c + ": " + Colourise.bg("string", c));
        }

        for (String f : foreground.keySet()) {
            for (String b : background.keySet()) {
                System.out.println(f + " " + b + ": " + Colourise.all("Hello World", f, b));
            }
        }
    }

}