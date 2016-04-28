    /**
     * Method for reducing the number of pixels in an image,
     * groups of 6 36 pixels are combined to give an 
     * average colour and the output image can then be
     * formed of Rubiks Cubes.
     */
    public static void groupedImage(BufferedImage image){
        int height = getHeight(image);
        int width =getWidth(image);
        double red=0;
        double blue=0;
        double green=0;
        int red1=0;
        int blue3=0;
        int green2=0;
        for (int x = 0; x < width; x+=6) {
            for (int y = 0; y < height; y+=6) {

                for(int i=x; i<x+6; i++){
                    for(int j=y; j<y+6; j++){
                        int rgba = image.getRGB(i, j);
                        Color col = new Color(rgba, true);
                        red+=col.getRed();
                        blue+=col.getBlue();
                        green+=col.getGreen();
                    }
                }
                red1=(int)(red/36.0);
                green2=(int)(green/36.0);
                blue3=(int)(blue/36.0);

                for(int k=x; k<x+6; k++){
                    for(int l=y; l<y+6; l++){
                        int rgba =image.getRGB(k,l);
                        Color col =new Color(rgba,true);
                        col = new Color(red1,
                            green2,
                            blue3);
                        image.setRGB(k, l, col.getRGB());
                    }
                }
                red=0;
                blue=0;
                green=0;
            }
        }
    }