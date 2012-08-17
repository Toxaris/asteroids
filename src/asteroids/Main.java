/**
 * 
 */
package asteroids;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferStrategy;

import javax.swing.Timer;

/**
 * The main window.
 * 
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
				System.exit(0);
			}
		} else {
			main.runWindowed();
		}
	}

	private final Control control;

	private boolean fullScreen;
	private final Physics physics;

	private final State state;

	public Main() {
		state = new State();
		physics = new Physics(state);
		control = new Control(state);

		state.addElement(new Ship(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0));

		setSize(400, 300);
		setBackground(Color.black);

		addKeyListener(control);
		addWindowListener(control);
	}

	@Override
	public void paint(final Graphics g) {
		final Graphics2D g2d = (Graphics2D) g;
		if (!fullScreen) {
			render(g2d);
			super.paint(g);
		}
	}

	private void render(final Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		final int width = getWidth();
		final int height = getHeight();
		final int size = Math.min(width, height);
		final double factor = size / 200.0;

		g.translate(0.5 * width, 0.5 * height);
		g.scale(factor, -factor);
		g.setClip(new Ellipse2D.Double(-100, -100, 200, 200));
		final AffineTransform transform = g.getTransform();
		for (final Element element : state.getElements()) {
			element.draw(g);
			g.setTransform(transform);
		}
	}

	/**
	 * 
	 */
	private void runFullScreen() {
		fullScreen = true;

		createBufferStrategy(2);
		final BufferStrategy bufferStrategy = getBufferStrategy();

		physics.start();
		while (control.isRunning()) {
			control.control();
			physics.simulate();
			final Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
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
				if (control.isRunning()) {
					control.control();
					physics.simulate();
					repaint();
				} else {
					dispose();
					System.exit(0);
				}
			}
		});

		setVisible(true);
		timer.start();
		physics.start();
	}
}
