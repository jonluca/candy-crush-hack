package logic;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/*(c) 2017 JonLuca De Caro
 *
Below is the logic for the actual hack

All the offsets are below. They are converted to byte offset location for RandomAccessFile

note: it appears King stores their variables in int16s rather than regular ints.
I'm not sure why, because you would either go for short or an int. int16 is quite an odd choice ¯\_(ツ)_/¯

Just 2 bytes, though

addendum: it seems like all the offsets are 12 bytes off of each other, except for lives. This might come in handy
in the future when more types of bonuses get added

0x00000290 00 – Lives
0x00000050 00 – Color Bomb
0x00000050 0C – Jelly Fish
0x00000060 08 – Coconut Roller
0x00000070 04 – Lollipop Hammer
0x00000090 08 – Lucky Candy
0x000000A0 04 – Wrapped and Striped
More?
0x000000B0 00 - Hand Swap
0x000000D0 04 - UFO
0x000000E0 00 - Paint

*/

public class HexEdits {
	private File file;
	private JFrame origin;
	private int[] selectedValues;

	public HexEdits(final JFrame origin, final File file, final int[] selectedValues) throws IOException {
		this.origin = origin; //sets current JFrame, hacky way of getting from action listener
		this.file = file; //sets save file
		this.selectedValues = selectedValues; //sets selectedVals
		editFile();
	}

	private void editFile() throws IOException {
		if (file != null) {

			final RandomAccessFile raf = new RandomAccessFile(file, "rw");
			try {
				// Color Bomb
				raf.seek(80); // Go to byte at offset position 80.

				final byte[] data = new byte[2]; // initialize 2 byte array.

				data[0] = (byte) (selectedValues[1] & 0xFF); // byte mask to get first byte
				data[1] = (byte) ((selectedValues[1] >> 8) & 0xFF);// shifted by a byte

				raf.write(data); //write the user selection

				// Jelly
				raf.seek(92);

				data[0] = (byte) (selectedValues[2] & 0xFF);
				data[1] = (byte) ((selectedValues[2] >> 8) & 0xFF);

				raf.write(data);

				// Coconut
				raf.seek(104);

				data[0] = (byte) (selectedValues[3] & 0xFF);
				data[1] = (byte) ((selectedValues[3] >> 8) & 0xFF);

				raf.write(data);

				// Lollipop
				raf.seek(116);

				data[0] = (byte) (selectedValues[4] & 0xFF);
				data[1] = (byte) ((selectedValues[4] >> 8) & 0xFF);

				raf.write(data);

				// Lucky
				raf.seek(152);

				data[0] = (byte) (selectedValues[5] & 0xFF);
				data[1] = (byte) ((selectedValues[5] >> 8) & 0xFF);

				raf.write(data);

				// Wrapped
				raf.seek(164);

				data[0] = (byte) (selectedValues[6] & 0xFF);
				data[1] = (byte) ((selectedValues[6] >> 8) & 0xFF);

				raf.write(data);

				// Hand
				raf.seek(176);

				data[0] = (byte) (selectedValues[7] & 0xFF);
				data[1] = (byte) ((selectedValues[7] >> 8) & 0xFF);

				raf.write(data);

				// UFO
				raf.seek(212);

				data[0] = (byte) (selectedValues[8] & 0xFF);
				data[1] = (byte) ((selectedValues[8] >> 8) & 0xFF);

				raf.write(data);

				// paint
				raf.seek(224);

				data[0] = (byte) (selectedValues[9] & 0xFF);
				data[1] = (byte) ((selectedValues[9] >> 8) & 0xFF);

				raf.write(data);

				// Lives
				raf.seek(656);

				data[0] = (byte) (selectedValues[0] & 0xFF);
				data[1] = (byte) ((selectedValues[0] >> 8) & 0xFF);

				raf.write(data);

			} finally {
				raf.close();

				// If you got here, then it was a success
				JOptionPane.showMessageDialog(origin, "Success! Simply place your save_##########.dat back in the data folder for Candy Crash Saga ", "Success", JOptionPane.PLAIN_MESSAGE);
			}

		}
	}

}
