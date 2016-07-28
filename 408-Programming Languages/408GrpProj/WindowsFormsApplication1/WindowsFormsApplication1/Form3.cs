using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace WindowsFormsApplication1
{
    public partial class Form3 : Form
    {
        bool player;
        int x = 0;
        string currentColor = "white";
        public Form3()
        {

            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            writeLetter(sender);

        }

        private void button2_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button3_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button4_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button5_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button6_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button7_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button8_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void button9_Click(object sender, EventArgs e)
        {
            writeLetter(sender);
        }

        private void playerToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        // Reset menu item
        private void playersToolStripMenuItem_Click(object sender, EventArgs e)
        {
            x = 0;
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
            string s = "";
            for (int i = 0; i < 9; i++)
            {
                s = s + " ";
                buttons[i].Text = s;
                buttons[i].Enabled = true;
                if(currentColor.Equals("white"))
                    buttons[i].BackColor = System.Drawing.Color.White;
                if (currentColor.Equals("blue"))
                    buttons[i].BackColor = System.Drawing.Color.Blue;
                if (currentColor.Equals("gree"))
                    buttons[i].BackColor = System.Drawing.Color.Green;
            }
            player = true;
            label1.Text = "Player 1's Turn";
        }

        private void menuStrip1_ItemClicked(object sender, ToolStripItemClickedEventArgs e)
        {
            
        }
        private void newGameToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }
        private void winConditions()
        {
            int myInt = (player) ? 1 : 0;
            myInt += 1;
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
            if (button1.Text.Equals(button2.Text) && button3.Text.Equals(button2.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button1.BackColor = System.Drawing.Color.Red;
                button3.BackColor = System.Drawing.Color.Red;
                button2.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button4.Text.Equals(button5.Text) && button6.Text.Equals(button5.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button4.BackColor = System.Drawing.Color.Red;
                button5.BackColor = System.Drawing.Color.Red;
                button6.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button7.Text.Equals(button8.Text) && button9.Text.Equals(button8.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button7.BackColor = System.Drawing.Color.Red;
                button8.BackColor = System.Drawing.Color.Red;
                button9.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button1.Text.Equals(button4.Text) && button4.Text.Equals(button7.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button1.BackColor = System.Drawing.Color.Red;
                button4.BackColor = System.Drawing.Color.Red;
                button7.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button2.Text.Equals(button5.Text) && button5.Text.Equals(button8.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button2.BackColor = System.Drawing.Color.Red;
                button5.BackColor = System.Drawing.Color.Red;
                button8.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button3.Text.Equals(button6.Text) && button9.Text.Equals(button6.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button3.BackColor = System.Drawing.Color.Red;
                button6.BackColor = System.Drawing.Color.Red;
                button9.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button1.Text.Equals(button5.Text) && button5.Text.Equals(button9.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button1.BackColor = System.Drawing.Color.Red;
                button5.BackColor = System.Drawing.Color.Red;
                button9.BackColor = System.Drawing.Color.Red;
                disable();
            }
            if (button3.Text.Equals(button5.Text) && button5.Text.Equals(button7.Text))
            {
                label1.Text = "Player " + myInt + " Wins!";
                button5.BackColor = System.Drawing.Color.Red;
                button3.BackColor = System.Drawing.Color.Red;
                button7.BackColor = System.Drawing.Color.Red;
                disable();
            }
            
        }



        private void writeLetter(object sender)
        {
            Button btn = sender as Button;
            player = !player;
            if (player)
            {
                btn.Text = ("O");
                label1.Text = "Player 2's Turn";

            }
            else
            {
                btn.Text = ("X");
                label1.Text = "Player1's Turn";
            }
            x++;
            btn.Enabled = false;
            if (x == 9)
                label1.Text = "Draw";
            winConditions();

        }
        private void disable()
        {
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };

            for (int i = 0; i < 9; i++)
            {
                buttons[i].Enabled = false;


            }
        }
        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void changeTileColorToolStripMenuItem_Click(object sender, EventArgs e)
        {

        }

        private void whiteToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
            for (int i = 0; i < 9; i++)

                buttons[i].BackColor = System.Drawing.Color.White;
            currentColor = "white";
        }

        private void blueToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
            for (int i = 0; i < 9; i++)

                buttons[i].BackColor = System.Drawing.Color.Blue;
            currentColor = "blue";
        }

        private void greenToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Button[] buttons = { button1, button2, button3, button4, button5, button6, button7, button8, button9 };
            for (int i = 0; i < 9; i++)

                buttons[i].BackColor = System.Drawing.Color.Green;
            currentColor = "green";
        }
    }
}
