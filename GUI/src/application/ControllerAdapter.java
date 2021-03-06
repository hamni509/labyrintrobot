package application;

public class ControllerAdapter {
	private boolean rightPressed = false;
	private boolean upPressed = false;
	private boolean leftPressed = false;
	private boolean downPressed = false;

	private final BluetoothAdapter bluetoothAdapter;

	public ControllerAdapter(BluetoothAdapter bluetoothAdapter) {
		this.bluetoothAdapter = bluetoothAdapter;
	}

	public void pressRight() {
		if (rightPressed == false) {
			rightPressed = true;
			revalidateButtons();
		}
	}

	public void releaseRight() {
		if (rightPressed) {
			rightPressed = false;
			revalidateButtons();
		}
	}

	public void pressUp() {
		if (upPressed == false) {
			upPressed = true;
			revalidateButtons();
		}
	}


	public void releaseUp() {
		if (upPressed) {
			upPressed = false;
			revalidateButtons();
		}
	}

	public void pressLeft() {
		if (leftPressed == false) {
			leftPressed = true;
			revalidateButtons();
		}
	}

	public void releaseLeft() {
		if (leftPressed) {
			leftPressed = false;
			revalidateButtons();
		}
	}

	public void pressDown() {
		if (downPressed == false) {
			downPressed = true;
			revalidateButtons();
		}
	}

	public void releaseDown() {
		if (downPressed) {
			downPressed = false;
			revalidateButtons();
		}
	}

	public void pressC() {
		final byte header = 0x02;
		final byte data = 0x00;

		bluetoothAdapter.sendMessage(header, data);
	}

	public void releaseC() {
		// Do nothing
	}

	private void revalidateButtons() {
		final byte header = 0x00;
		final byte data;

		if (upPressed) {
			if (rightPressed == leftPressed) {
				System.out.println("up");
				data = 0x00;
			} else {
				if (rightPressed) {
					System.out.println("right");
					data = 0x02;
				} else {
					System.out.println("left");
					data = 0x03;
				}
			}
		} else {
			if (downPressed) {
				System.out.println("back");
				data = 0x01;
			} else if (rightPressed != leftPressed) {
				if (rightPressed) {
					System.out.println("rotate right");
					data = 0x04;
				} else {
					System.out.println("rotate left");
					data = 0x05;
				}
			} else {
				System.out.println("stop");
				data = 0x06;
			}
		}

		bluetoothAdapter.sendMessage(header, data);
	}
}
