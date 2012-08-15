/**
 * 
 */
package asteroids;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;

/**
 * @author Tillmann Rendel
 */
public class Main extends Frame {
	public static void main(final String[] args) {
		final Main main = new Main();

		final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

		if (device.isFullScreenSupported()) {
			try {
				main.setUndecorated(true);
				main.setIgnoreRepaint(true);
				device.setFullScreenWindow(main);
				main.runFullScreen();
				main.setVisible(false);
			} finally {
				device.setFullScreenWindow(null);
				main.dispose();
			}
		} else {
			main.runWindowed();
		}
	}

	private boolean done;
	private boolean fullScreen;
	List<Element> elements = new ArrayList<Element>();

	public Main() {
		elements.add(new Asteroid(100.0, 100.0, 0.0, 0.0, 0.01, 0.0, 0.0, Color.blue, 50.0));

		setSize(400, 300);

		setBackground(Color.black);
	}

	@Override
	public void paint(final Graphics g) {
		if (!fullScreen) {
			render(g);
			super.paint(g);
		}
	}

	private void render(final Graphics g) {
		for (final Element element : elements) {
			element.render(g);
		}
	}

	/**
	 * 
	 */
	private void runFullScreen() {
		fullScreen = true;
		addKeyListener(new KeyAdapter() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public void keyReleased(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					done = true;
				}
			}
		});

		createBufferStrategy(2);
		final BufferStrategy bufferStrategy = getBufferStrategy();

		while (!done) {
			tick();
			final Graphics g = bufferStrategy.getDrawGraphics();
			try {
				g.setColor(Color.black);
				g.fillRect(0, 0, getWidth(), getHeight());
				render(g);
			} finally {
				g.dispose();
			}
			bufferStrategy.show();
		}
	}

	/**
	 * 
	 */
	private void runWindowed() {
		final Timer timer = new Timer(30, new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
				tick();
			}
		});

		timer.start();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent e) {
				dispose();
			}
		});

		setVisible(true);
	}

	/**
	 * 
	 */
	protected void tick() {
		for (final Element element : elements) {
			element.tick();
		}
		repaint();
	}
}
