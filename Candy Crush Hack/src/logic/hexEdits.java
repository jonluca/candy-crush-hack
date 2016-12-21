package logic;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*(c) 2017 JonLuca De Caro
 * 
Below is the logic for the actual hack

All the offsets are below. They are converted to byte offset location for RandomAccessFile 

0x00000290 00 – Lives
0x00000050 00 – Color Bomb
0x00000050 0C – Jelly Fish
0x00000060 08 – Coconut Roller
0x00000070 04 – Lollipop Hammer
0x00000090 08 – Lucky Candy
0x000000A0 04 – Wrapped and Striped

*/

public class hexEdits {
    private File file;
    int livesInt, colorInt, jellyInt, coconutInt, lollipopInt, luckyInt, wrappedInt;

    public hexEdits(File file, int livesInt, int colorInt, int jellyInt, int coconutInt, int lollipopInt, int luckyInt,
	    int wrappedInt) throws IOException {
	this.livesInt = livesInt;
	this.colorInt = colorInt;
	this.jellyInt = jellyInt;
	this.coconutInt = coconutInt;
	this.lollipopInt = lollipopInt;
	this.luckyInt = luckyInt;
	this.wrappedInt = wrappedInt;

	this.file = file;
	editFile();
    }

    private void editFile() throws IOException {
	if (file != null) {

	    RandomAccessFile raf = new RandomAccessFile(file, "rw");
	    try {
		// Color Bomb
		raf.seek(80); // Go to byte at offset position 80.

		byte[] data = new byte[2];

		data[0] = (byte) (colorInt & 0xFF);
		data[1] = (byte) ((colorInt >> 8) & 0xFF);

		raf.write(data);

		// Jelly
		raf.seek(92);

		data[0] = (byte) (jellyInt & 0xFF);
		data[1] = (byte) ((jellyInt >> 8) & 0xFF);

		raf.write(data);

		// Coconut
		raf.seek(104);

		data[0] = (byte) (livesInt & 0xFF);
		data[1] = (byte) ((livesInt >> 8) & 0xFF);

		raf.write(data);

		// Lollipop
		raf.seek(116);

		data[0] = (byte) (livesInt & 0xFF);
		data[1] = (byte) ((livesInt >> 8) & 0xFF);

		raf.write(data);

		// Lucky
		raf.seek(152);

		data[0] = (byte) (livesInt & 0xFF);
		data[1] = (byte) ((livesInt >> 8) & 0xFF);

		raf.write(data);

		// Wrapped
		raf.seek(164);

		data[0] = (byte) (livesInt & 0xFF);
		data[1] = (byte) ((livesInt >> 8) & 0xFF);

		raf.write(data);

		// Lives
		raf.seek(656);

		data[0] = (byte) (livesInt & 0xFF);
		data[1] = (byte) ((livesInt >> 8) & 0xFF);

		raf.write(data);

	    } finally {
		raf.close();
	    }

	}
    }

}
