package simulation;

import java.text.DecimalFormat;
import java.util.Random;

public class RandomGen {
    private Random random = new Random(19);

    public Boolean nextBoolean(){
        return random.nextBoolean();
    }

    public Integer nextInt(){
        Integer integer = random.nextInt(5) * (random.nextBoolean() ? -1 : 1);
        return integer;
    }
    public Double nextDouble(){
        float leftLimit = -5F;
        float rightLimit = 5F;
        DecimalFormat df = new DecimalFormat("0.0");
        String s = df.format(leftLimit + random.nextDouble() * (rightLimit - leftLimit));
        return Double.parseDouble(s);
    }
    public String nextString(){
        return "it is not dealt with";
    }
}
