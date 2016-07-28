using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace _408GrpProj
{
    public partial class Form2 : Form
    {
        public Form2()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            var hangman = new Form1();
            hangman.Show();
            this.Hide();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            Application.Exit();
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            var tictactoe = new WindowsFormsApplication1.Form3();
            tictactoe.Show();
            this.Hide();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            var snake = new SnakeGame.Form4();
            snake.Show();
            this.Hide();
        }
    }
}
