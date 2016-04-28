 /**
     * Method for simple conversion of an image to a Rubiks cube image.
     * Uses least distance to a point formula and measures the distance
     * from the following colours:     
    White =(255,255,255), Red =(255,0,0), Blue=(0,0,255),
    Green=(0,255,0), Orange=(255,128,0)
    Yellow=(255,255,0)
     */
    public static void naiveRubiks(BufferedImage image){
        int height = getHeight(image);
        int width = getWidth(image);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                int redC= col.getRed();
                int greenC= col.getGreen();
                int blueC= col.getBlue();
                double[] cols = {0.00,0.00,0.00,0.00,0.00,0.00};
                double min=10000000000000000000.00;
                int colourtc=0;

                cols[0] = Math.abs(redC-255) + 
                Math.abs(greenC-255) + Math.abs(blueC-255);
                cols[1] = Math.abs(redC-255) +
                Math.abs(greenC-0) + Math.abs(blueC-0);
                cols[2] = Math.abs(redC-0) + 
                Math.abs(greenC-0) + Math.abs(blueC-255);
                cols[3] = Math.abs(redC-60) +
                Math.abs(greenC-160) + Math.abs(blueC-0);
                cols[4] = Math.abs(redC-255) +
                Math.abs(greenC-128) + Math.abs(blueC-0);
                cols[5] = Math.abs(redC-220) +
                Math.abs(greenC-220) + Math.abs(blueC-0);
                
                for(int i=0; i<6; i++){
                    if(cols[i]<min){
                        min=cols[i];
                        colourtc=i;
                    }
                }

                if(colourtc==0){
                    col = new Color(255,255,255);
                }
                else if(colourtc==1){
                    col = new Color(255,0,0);
                }
                else if(colourtc==2){
                    col = new Color(0,0,255);
                }
                else if(colourtc==3){
                    col = new Color(102,204,0);
                }
                else if(colourtc==4){
                    col = new Color(255,128,0);
                }
                else if(colourtc==5){
                    col = new Color(255,255,0);
                }

                image.setRGB(x, y, col.getRGB());
            }
        }
    }