package Controller;
/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import Model.entities.Cowboy;
import Utils.Vector2;
import View.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {

  Game m_game;
  VirtualInput vi;

  public CanvasListener(Game game) {
    m_game = game;
    vi = new VirtualInput();
  }
  public VirtualInput getVirtualInput() {
	  return vi;
  }
  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {
    vi.updateKeys(-e.getButton(), true);
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
    vi.updateKeys(-e.getButton(), false);
  }

  @Override
  public void mouseEntered(MouseEvent e) {
  }

  @Override
  public void mouseExited(MouseEvent e) {
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    
    vi.updateMouse(e.getX(), e.getY());
    
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    
    vi.updateMouse(e.getX(), e.getY());
  }

  @Override
  public void keyTyped(KeyEvent e) {

  }

  @Override
  public void keyPressed(KeyEvent e) {
    vi.updateKeys(e.getKeyCode(), true);
  }

  @Override
  public void keyReleased(KeyEvent e) {
    vi.updateKeys(e.getKeyCode(), false);
  }

  @Override
  public void tick(long elapsed) {
    m_game.tick(elapsed);
    
  }

  @Override
  public void paint(Graphics g) {
    m_game.paint(g);
  }

  @Override
  public void windowOpened() {
    //m_game.loadMusic();
//    m_game.m_canvas.setTimer(6000);
	}

	@Override
	public void exit() {
	}

//  boolean m_expired;
	@Override
	public void endOfPlay(String name) {
//    if (!m_expired) // only reload if it was a forced reload by timer
		// m_game.loadMusic();
//    m_expired = false;
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
//    System.out.println("Forcing an early change of music");
//    m_expired = true;
//    m_game.loadMusic();    
	}

}
