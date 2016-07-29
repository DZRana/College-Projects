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
    public partial class Form1 : Form
    {
        List<Label> labels = new List<Label>();
        public string chosenWord = "";
        public string BlankChar { get { return "__"; } }
        String[] easyWords = { "rank", "cent", "dwarf", "pill" };
        String[] medWords = { "blankets", "dinosaur", "ascended", "brownies" };
        String[] hardWords = { "razzmatazzes", "incentivized", "embezzlement", "nonoxidizing" };
        Random r = new Random();
        Image hm1 = Image.FromFile("hm1.jpg");
        Image hm2 = Image.FromFile("hm2.jpg");
        Image hm3 = Image.FromFile("hm3.jpg");
        Image hm4 = Image.FromFile("hm4.jpg");
        Image hm5 = Image.FromFile("hm5.jpg");
        Image hm6 = Image.FromFile("hm6.jpg");
        Image hm7 = Image.FromFile("hm7.jpg");
        object syncLock = new object(); //PRESENTATION

        public Form1()
        {
            InitializeComponent();
            AddButtons();
            triesLeft.Text = "0";
            label2.Image = hm1;
        }

        private int generateRandom(int min, int max) //PRESENTATION
        {
            lock (syncLock) return r.Next(min, max);
        }

        private void AddButtons()
        {
            for (int i = (int)'a'; i <= (int)'z'; i++)
            {
                Button btn = new Button();
                btn.Text = ((char)i).ToString();
                btn.Parent = flowLayoutPanel1;
                btn.Font = new Font(FontFamily.GenericMonospace, 15, FontStyle.Bold);
                btn.Size = new Size(30, 30); 
                btn.BackColor = Color.Goldenrod;
                btn.Click += onBtnClick;
            }
            flowLayoutPanel1.Enabled = false;
        }

        private void onBtnClick(object sender, EventArgs e)
        {
            Button btn = (Button)sender;
            char selected = btn.Text.ToCharArray()[0];
            btn.Enabled = false;
            if (chosenWord.Contains(selected))
            {
                char[] charArr = chosenWord.ToCharArray();
                for (int i = 0; i < chosenWord.Length; i++)
                {
                    if (charArr[i] == selected)
                    {
                        labels[i].Font = new Font(FontFamily.GenericMonospace, 20, FontStyle.Bold);
                        labels[i].Text = selected.ToString();
                    }
                }
                if (labels.Where(x => x.Text.Equals(BlankChar)).Any()) return; //PRESENTATION LAMBDA. Enumerator
                label3.Font = new Font(FontFamily.GenericSansSerif, 40, FontStyle.Italic);
                label3.ForeColor = Color.Green;
                label3.Text = "You Win!";
                flowLayoutPanel1.Enabled = false;
            }
            else
            {
                int tL = Convert.ToInt32(triesLeft.Text); //PRESENTATION
                switch(tL) // PRESENTATION
                {
                    case 6:
                        label2.Image = hm2;
                        break;
                    case 5:
                        label2.Image = hm3;
                        break;
                    case 4:
                        label2.Image = hm4;
                        break;
                    case 3:
                        label2.Image = hm5;
                        break;
                    case 2:
                        label2.Image = hm6;
                        break;
                    case 1:
                        label2.Image = hm7;
                        break;
                }
                tL -= 1;
                if (tL == 0)
                {
                    flowLayoutPanel1.Enabled = false;
                    label3.Font = new Font(FontFamily.GenericSansSerif, 40, FontStyle.Italic);
                    label3.ForeColor = Color.Red;
                    label3.Text = "You Lose!";
                    for (int i = 0; i < chosenWord.Length; i++)
                    {
                        if (labels[i].Text.Equals(BlankChar))
                        {
                            labels[i].Font = new Font(FontFamily.GenericMonospace, 20, FontStyle.Bold);
                            labels[i].ForeColor = Color.Red;
                            labels[i].Text = chosenWord[i].ToString();
                        }
                    }
                }
                triesLeft.Text = tL.ToString();
            }
        }

        private void easyToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ResetGame();   
            chosenWord = easyWords[generateRandom(0,easyWords.Length - 1)];
            AddLabels();
        }

        private void mediumToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ResetGame();
            chosenWord = medWords[generateRandom(0, medWords.Length - 1)];
            AddLabels();
        }

        private void hardToolStripMenuItem_Click(object sender, EventArgs e)
        {
            ResetGame();
            chosenWord = hardWords[generateRandom(0, hardWords.Length - 1)]; //PRESENTATION
            AddLabels();
        }

        private void exitToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
            var menu = new Form2();
            menu.Show();
        }

        private void ResetGame()
        {
            flowLayoutPanel1.Controls.Clear();
            AddButtons();
            panel1.Invalidate();
           
            triesLeft.Text = "6";
            label2.Image = hm1;
            label3.Text = "";
            flowLayoutPanel1.Enabled = true;
        }

        private void AddLabels()
        {
            groupBox1.Controls.Clear();
            labels.Clear();
            char[] wordCharArr = chosenWord.ToCharArray();
            int labelDims = groupBox1.Width / wordCharArr.Length;

            for (int i = 0; i < wordCharArr.Length; i++)
            {
                Label l = new Label();        
                l.Text = BlankChar;
                l.Location = new Point(10 + i * labelDims, groupBox1.Height - 30);
                l.Parent = groupBox1;
                l.BringToFront();
                labels.Add(l);
            }
        }
    }
}
