using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Timers;

namespace SnakeGame
{
    public partial class Form4 : Form
    {
        private List<Object> Snake = new List<Object>();
        private Object food = new Object();
        public Form4()
        {
            InitializeComponent();
            new Settings();
            //Game speed
            gameTimer.Interval = 1000 / Settings.speed;
            gameTimer.Tick += UpdateScreen;
            gameTimer.Start();

    
            startGame();

        }

        private void startGame()
        {
            new Settings();
            labelGameStatus.Visible = false;

            //position of snake
            Snake.Clear();
            Object head = new Object();
            head.x = 10;
            head.y = 5;

            Snake.Add(head);

            lblScore.Text = Settings.score.ToString();
            generateFood();

        }
        private void generateFood()
        {
            Random rand = new Random();
            int maxXPos = gameBoard.Size.Width / Settings.width;
            int maxYPos = gameBoard.Size.Height / Settings.height;

            food = new Object();
            food.x = rand.Next(0, maxXPos);
            food.y = rand.Next(0, maxYPos);
        }
        private void UpdateScreen(object sender, EventArgs e)
        {
            if (Settings.gameOver)
            {
                if (Input.KeyPressed(Keys.Enter))
                    startGame();
            }
            else
            {
                if (Input.KeyPressed(Keys.Right) && Settings.direction != Direction.LEFT)
                    Settings.direction = Direction.RIGHT;
                else if (Input.KeyPressed(Keys.Right) && Settings.direction != Direction.LEFT)
                    Settings.direction = Direction.RIGHT;
                else if (Input.KeyPressed(Keys.Left) && Settings.direction != Direction.RIGHT)
                    Settings.direction = Direction.LEFT;
                else if (Input.KeyPressed(Keys.Up) && Settings.direction != Direction.DOWN)
                    Settings.direction = Direction.UP;
                else if (Input.KeyPressed(Keys.Down) && Settings.direction != Direction.UP)
                    Settings.direction = Direction.DOWN;

                movePlayer();
            }
            //Refreshes game board
            gameBoard.Invalidate();

        }

        private void gameBoard_Paint(object sender, PaintEventArgs e)
        {
            Graphics canvas = e.Graphics;
            Brush snakeColor;
            Random randomGen = new Random();
            KnownColor[] names = (KnownColor[])Enum.GetValues(typeof(KnownColor));
            KnownColor randomColorName = names[randomGen.Next(names.Length)];

            if (!Settings.gameOver)
            {
                for(int i = 0; i < Snake.Count; i++)
                {
                    if (i == 0)
                        snakeColor = new SolidBrush(Color.FromKnownColor(randomColorName));
                    else
                        snakeColor = new SolidBrush(Color.FromKnownColor(randomColorName));

                    //Render Snake
                    canvas.FillEllipse(snakeColor,
                        new Rectangle(Snake[i].x * Settings.width,
                                      Snake[i].y * Settings.height,
                                      Settings.width, Settings.height));

                    //Render food object
                    canvas.FillEllipse(new SolidBrush(Color.FromKnownColor(randomColorName)),
                        new Rectangle(food.x * Settings.width,
                                      food.y * Settings.height,
                                      Settings.width, Settings.height));
                }
            }
            else
            {
                string gameStatus = "\nYour final score is: " +
                    Settings.score + "\nPress Enter to play again!";
                labelGameStatus.Text = gameStatus;
                labelGameStatus.Visible = true;
            }
        }
        private void movePlayer()
        {
            for(int i = Snake.Count - 1; i >= 0; i--)
            {
                if(i == 0)
                {
                    switch (Settings.direction)
                    {
                        case Direction.RIGHT:
                            Snake[i].x++;
                            break;
                        case Direction.LEFT:
                            Snake[i].x--;
                            break;
                        case Direction.UP:
                            Snake[i].y--;
                            break;
                        case Direction.DOWN:
                            Snake[i].y++;
                            break;
                    }

                    int maxXPos = gameBoard.Size.Width / Settings.width;
                    int maxYPos = gameBoard.Size.Height / Settings.height;

                    //Collision with walls
                    if(Snake[i].x < 0 || Snake[i].y < 0 ||
                        Snake[i].x >= maxXPos || Snake[i].y >= maxYPos)
                    {
                        Die();
                    }

                    //Collission with walls
                    for(int j = 1; j < Snake.Count; j++)
                    {
                        if (Snake[i].x == Snake[j].x &&
                            Snake[i].y == Snake[j].y)

                            Die();
                    }

                    //Eat object
                    if (Snake[0].x == food.x && Snake[0].y == food.y)
                    {
                        Eat();
                    }
                }
                else
                {
                    Snake[i].x = Snake[i - 1].x;
                    Snake[i].y = Snake[i - 1].y;
                }
            }
        }

        private void Form4_KeyDown(object sender, KeyEventArgs e)
        {
            Input.ChangeState(e.KeyCode, true);
        }

        private void Form4_KeyUp(object sender, KeyEventArgs e)
        {
            Input.ChangeState(e.KeyCode, false);
        }
        private void Die()
        {
            Settings.gameOver = true;
        }
        private void Eat()
        {
            Object food = new Object();
            food.x = Snake[Snake.Count - 1].x;
            food.y = Snake[Snake.Count - 1].y;

            Snake.Add(food);
            Settings.score += Settings.points;
            lblScore.Text = Settings.score.ToString();

            generateFood();

        }

    }
}
