/**
     * The following four methods all applying random dithering
     * to an image with varying possible colour levels.
     */
    public static void halfTone(BufferedImage image){
        int height = getHeight(image);
        int width = getWidth(image);
        int half=255;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                int greyLevel = (col.getRed()+col.getGreen()+
                        col.getBlue())/3;
                Random random =new Random();
                int prob = random.nextInt(256)+1;
                if(greyLevel<prob){
                    half=0;
                }
                else{
                    half=255;
                }
                col = new Color(half,half,half);
                image.setRGB(x, y, col.getRGB());
            }
        }
    }