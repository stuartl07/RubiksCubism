private static void stuckiDithering(BufferedImage img) {

        C3[] palette = new C3[] {
                //new C3(  0,   0,   0),
                new C3(  0,   0, 255),
                new C3(  0, 255,   0),
                //new C3(  0, 255, 255),
                new C3(255,   0,   0),
                new C3(255,   175, 91),
                new C3(255, 255,   0),
                new C3(255, 255, 255)
            };

        int w = img.getWidth();
        int h = img.getHeight();

        C3[][] d = new C3[h][w];

        for (int y = 0; y < h; y++) 
            for (int x = 0; x < w; x++) 
                d[y][x] = new C3(img.getRGB(x, y));

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {

                C3 oldColor = d[y][x];
                C3 newColor = findClosestPaletteColor(oldColor, palette);
                img.setRGB(x, y, newColor.toColour().getRGB());

                C3 err = oldColor.sub(newColor);

                if (x+1 < w) {
                    d[y  ][x+1] = d[y  ][x+1].add(err.mul(8./42));}
                if (x+2 < w) {
                    d[y  ][x+2] = d[y  ][x+2].add(err.mul(4./42));}
                if (x-1>=0 && y+1<h){
                    d[y+1][x-1] = d[y+1][x-1].add(err.mul(4./42));}
                if (x-2>=0 && y+1<h){
                    d[y+1][x-2] = d[y+1][x-2].add(err.mul(2./42));}
                if (y+1 < h)  {
                    d[y+1][x  ] = d[y+1][x  ].add(err.mul(8./42));}
                if (x+1<w && y+1<h){
                    d[y+1][x+1] = d[y+1][x+1].add(err.mul(4./42));}
                if (x+2<w && y+1<h){
                    d[y+1][x+2] = d[y+1][x+2].add(err.mul(2./42));}
                if (x-2>=0 && y+2<h){
                    d[y+2][x-2] = d[y+2][x-2].add(err.mul(1./42));}
                if (x-1>=0 && y+2<h){
                    d[y+2][x-1] = d[y+2][x-1].add(err.mul(2./42));}
                if (y+2 < h){
                    d[y+2][x] = d[y+2][x].add(err.mul(4./42));}
                if (x+1<w && y+2<h){
                    d[y+2][x+1] = d[y+2][x+1].add(err.mul(2./42));}
                if (x+2<w && y+2<h) {
                    d[y+2][x+2] = d[y+2][x+2].add(err.mul(1./42));}
            }
        }   
    }