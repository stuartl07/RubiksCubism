    private static void serpFloydSteinbergDithering(BufferedImage img) {
        C3[] palette = new C3[] {
                //new C3(  0,   0,   0),
                new C3(  0,   0, 255),
                new C3(  0, 255,   0),
                new C3(255,   0,   0),
                new C3(255,   175, 91),
                new C3(255, 255,   0),
                new C3(255, 255, 255)
            };

        int w = img.getWidth();
        int h = img.getHeight();
        int direction=0;

        C3[][] d = new C3[h][w];

        for (int y = 0; y < h; y++) 
            for (int x = 0; x < w; x++) 
                d[y][x] = new C3(img.getRGB(x, y));

        for (int y = 0; y < img.getHeight(); y++) {
            if((direction%2)==0){
                for (int x = 0; x < img.getWidth(); x++) {

                    C3 oldColor = d[y][x];
                    C3 newColor = findClosestPaletteColor(oldColor, palette);
                    img.setRGB(x, y, newColor.toColour().getRGB());

                    C3 err = oldColor.sub(newColor);

                    if (x+1 < w){ 
                        d[y  ][x+1] = d[y  ][x+1].add(err.mul(7./16));
                    }
                    if (x-1>=0 && y+1<h){ 
                        d[y+1][x-1] = d[y+1][x-1].add(err.mul(3./16));
                    }
                    if (y+1 < h){
                        d[y+1][x  ] = d[y+1][x  ].add(err.mul(5./16));
                    }
                    if (x+1<w && y+1<h){
                        d[y+1][x+1] = d[y+1][x+1].add(err.mul(1./16));
                    }
                    direction++;
                }
            }
            else{
                for (int x = img.getWidth()-1; x >=0; x--) {

                    C3 oldColor = d[y][x];
                    C3 newColor = findClosestPaletteColor(oldColor, palette);
                    img.setRGB(x, y, newColor.toColour().getRGB());

                    C3 err = oldColor.sub(newColor);

                    if (x-1>=0){
                        d[y  ][x-1] = d[y  ][x-1].add(err.mul(7./16));}
                    if (x-1>=0 && y+1<h){
                        d[y+1][x-1] = d[y+1][x-1].add(err.mul(1./16));}
                    if (y+1 < h){
                        d[y+1][x  ] = d[y+1][x  ].add(err.mul(5./16));}
                    if (x+1<w && y+1<h){
                        d[y+1][x+1] = d[y+1][x+1].add(err.mul(3./16));}
                    direction++;
                }
            }
        }   
    }