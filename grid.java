  /**
     * Method for applying a grid to an image to represent
     * the boundaries of each cube face.
     */
    public static void gridImage(BufferedImage image){
        int height = getHeight(image);
        int width = getWidth(image);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y+=6) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(0,0,0);
                image.setRGB(x, y, col.getRGB());
            }
        }
        for (int x = 0; x < width; x+=6) {
            for (int y = 0; y < height; y++) {
                int rgba = image.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(0,0,0);
                image.setRGB(x, y, col.getRGB());
            }
        }
    }