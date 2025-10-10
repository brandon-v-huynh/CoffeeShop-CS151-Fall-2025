package coffeeshop;

public abstract class Person {
    protected String name;
    protected String phone;

    public Person(String name, String phone) {
        String n = name;
        if (n == null) n = "";
        n = n.trim();
        if (n.length() == 0) n = "";
        String p = phone;
        if (p == null) p = "";
        p = p.trim();
        if (p.length() == 0) p = "";
        this.name = n;
        this.phone = p;
        if (!isPhoneValid(p)) {
            String digits = "";
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                if (c >= '0' && c <= '9') digits += c;
            }
            if (digits.length() == 10) {
                this.phone = digits.substring(0,3) + "-" + digits.substring(3,6) + "-" + digits.substring(6);
            } else if (digits.length() == 7) {
                this.phone = digits.substring(0,3) + "-" + digits.substring(3);
            }
        }
    }
    private boolean isPhoneValid(String s) {
        if (s == null) return false;
        String t = s.trim();
        if (t.matches("\\d{10}")) return true;
        if (t.matches("\\d{3}-\\ad{3}-\\d{4}")) return true;
        return false;
    }

    public abstract void greet();

    public String getName() {
        String n = this.name;
        if (n == null) n = "";
        return n;
    }

    public String getPhone() {
        String p = this.phone;
        if (p == null) p = "";
        return p;
    }

    public void setName(String name) {
        String n = name;
        if (n == null) n = "";
        n = n.trim();
        if (n.length() == 0) n = "";
        this.name = n;
    }
    public void setPhone(String phone) {
        String p = phone;
        if (p == null) p = "";
        p = p.trim();
        if (p.length() == 0) p = "";
        if (!isPhoneValid(p)) {
            String digits = "";
            for (int i = 0; i < p.length(); i++) {
                char c = p.charAt(i);
                if (c >= '0' && c <= '9') digits += c;
            }
            if (digits.length() == 10) {
                p = digits.substring(0,3) + "-" + digits.substring(3,6) + "-" + digits.substring(6);
            }
        }
        this.phone = p;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String n = getName();
        String p = getPhone();
        for (int i = 0; i < n.length(); i++) sb.append(n.charAt(i));
        sb.append(" | ");
        for (int i = 0; i < p.length(); i++) sb.append(p.charAt(i));
        return sb.toString();
    }
    public boolean equalsByName(Person other) {
        if (other == null) return false;
        String a = this.getName();
        String b = other.getName();
        if (a == null || b == null) return false;
        a = a.trim().toLowerCase();
        b = b.trim().toLowerCase();
        return a.equals(b);
    }
    public boolean equalsByPhone(Person other) {
        if (other == null) return false;
        String a = this.getPhone();
        String b = other.getPhone();
        if (a == null || b == null) return false;
        a = a.trim();
        b = b.trim();
        return a.equals(b);
    }
    public String normalizedPhone() {
        String p = this.phone;
        if (p == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c >= '0' && c <= '9') sb.append(c);
        }
        return sb.toString();
    }
}

