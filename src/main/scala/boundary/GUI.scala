package boundary

import java.awt.event.ActionEvent
import javax.swing.{JButton, JFrame, SwingUtilities}

class GUI():
  private val frame = JFrame()
  private val btn = JButton("click me!")
  frame.setSize(100,100)
  frame.add(btn)
  btn.addActionListener((e: ActionEvent) => println("cliccato"))
  frame.setVisible(true)

  def render(i: Int): Unit = SwingUtilities.invokeLater { () =>
    println(s"render - $i")
  }
