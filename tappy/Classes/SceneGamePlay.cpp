/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#include "SceneGamePlay.h"
#include "Globals.h"
#include "Obstacle.h"
#include "SceneGameOver.h"
#include <stdlib.h> //srand
#include <time.h>   //seed for srand
#include <math.h>   //floor
#include "SoundManager.h"
#include "ccUTF8.h"
#include <CCScheduler.h>

USING_NS_CC;

Scene *GamePlay::createScene()
{
    // create a scene with physics and setup the world
    auto sceneWithPhysics = Scene::createWithPhysics();

    // disable physics engine's gravity
    sceneWithPhysics->getPhysicsWorld()->setGravity(Vec2(0, 0));

    // create our scene and add it to the scene with physics
    auto scene = GamePlay::create();
    sceneWithPhysics->addChild(scene);

    return sceneWithPhysics;
}

bool GamePlay::init()
{
    // super init first
    if (!Scene::init()) {
        return false;
    }

    //clear color for background flickering (at joins)
    this->addChild(cocos2d::LayerColor::create(Color4B(213, 237, 247, 255)));

    isGameStarted = false;
    isObstacleStarted = false;

    // add "Touch to Play" label
    labelStart = Label::createWithTTF("Touch to Play", "fonts/Kenney Pixel.ttf", 20);
    labelStart->setScale(Director::getInstance()->getContentScaleFactor());
    labelStart->setPosition(
            visibleOrigin.x / 2 + visibleSize.width / 2,
            visibleOrigin.y / 2 +
            visibleSize.height / 2);
    labelStart->setColor(cocos2d::Color3B::GREEN);
    this->addChild(labelStart, 1);

    // create the background
    background = new Background();
    background->create(this);

    // add the ground and its physics
    auto ground = Sprite::create("ground.png");
    ground->setScale(Director::getInstance()->getContentScaleFactor());
    ground->setPosition(visibleOrigin.x + visibleSize.width / 2,
                        visibleOrigin.y + ground->getContentSize().height / 2);
    auto groundBox = PhysicsBody::createBox(ground->getContentSize());
    groundBox->setCollisionBitmask(BITMASK_GROUND);
    groundBox->setContactTestBitmask(true);
    groundBox->setDynamic(false);
    ground->setPhysicsBody(groundBox);
    this->addChild(ground, LAYER_GROUND);
    groundHeight = ground->getContentSize().height; //we'll need this for obstacles

    // add a physics box around the visible screen
    auto edgeBox = PhysicsBody::createEdgeBox(visibleSize, cocos2d::PHYSICSBODY_MATERIAL_DEFAULT, 5.0f);
    edgeBox->setCollisionBitmask(BITMASK_SCREEN);
    edgeBox->setContactTestBitmask(false);
    edgeBox->setDynamic(false);
    auto node = Node::create();
    node->setPosition(visibleSize.width / 2 + visibleOrigin.x,
                      visibleSize.height / 2 + visibleOrigin.y);
    node->setPhysicsBody(edgeBox);
    this->addChild(node);

    // add the player
    player = new Player(this);
    playerHeight = player->GetHeight();

    // enemy
    enemyEasy = new Enemy(this, groundHeight);
    enemyNormal = new Enemy(this, groundHeight);
    enemyHard = new Enemy(this, groundHeight);
    enemyImpossible = new Enemy(this, groundHeight);

    // create a pool of obstacles
    CreateObstacles();

    // game time (for obstacles)
    gameTime = 0;
    gameTimeElapsed = 0;
    gameTimeDefault = 2.25f;

    // collision detection
    auto contactListener = cocos2d::EventListenerPhysicsContact::create();
    contactListener->onContactBegin = CC_CALLBACK_1(GamePlay::onContactBegin, this);
    _eventDispatcher->addEventListenerWithSceneGraphPriority(contactListener, this);

    // touch listener (for the player to jump)
    auto playerListener = cocos2d::EventListenerTouchOneByOne::create();
    playerListener->setSwallowTouches(true);
    playerListener->onTouchBegan = CC_CALLBACK_2(GamePlay::onTouchBegan, this);
    _eventDispatcher->addEventListenerWithSceneGraphPriority(playerListener, this);

    // initialize player score
    score = 0;

    // create score label
    auto stringScore = StringUtils::format("Score: %i", (int) score);
    labelScore = Label::createWithTTF(stringScore.c_str(), "fonts/Kenney Pixel.ttf",
                                      SCALE_FACTOR_SCORE * visibleSize.height / 1.5f);
    labelScore->setPosition(visibleOrigin.x / 2 + visibleSize.width / 2,
                            visibleOrigin.y / 2 + visibleSize.height - labelScore->getContentSize().height / 2);
    labelScore->setColor(Color3B::WHITE);
    this->addChild(labelScore, LAYER_SCORE);

    // create the cloud
    cloud = new Cloud(this);

    return true;
}

// collision detection
bool GamePlay::onContactBegin(PhysicsContact &contact)
{
    // get bodies' collision bit masks in contact
    int a = contact.getShapeA()->getCollisionBitmask();
    int b = contact.getShapeB()->getCollisionBitmask();
    int c = contact.getShapeA()->getContactTestBitmask();
    int d = contact.getShapeB()->getContactTestBitmask();

    // check for contact (player with obstacles and ground)
    if (a == BITMASK_PLAYER && (b == BITMASK_OBSTACLE || b == BITMASK_SCREEN || b == BITMASK_GROUND) ||
        b == BITMASK_PLAYER && (a == BITMASK_OBSTACLE || a == BITMASK_SCREEN || a == BITMASK_GROUND)) {
        // game over man!
        GameOver();
    }

    // check for contact (player with enemies)
    if (a == BITMASK_PLAYER && (c == BITMASK_ENEMY || d == BITMASK_ENEMY) ||
        b == BITMASK_PLAYER && (c == BITMASK_ENEMY || d == BITMASK_ENEMY)) {
        // game over man!
        GameOver();
    }

    return false;
}

bool GamePlay::onTouchBegan(cocos2d::Touch *touch, cocos2d::Event *event)
{
    if (!isGameStarted) {
        isGameStarted = true;
        GameStart();
    }

    // player hop movement
    player->speed = PLAYER_FLY_SPEED;

    // play sfx
    SoundManager::Instance()->PlaySFX("sfx/fly.mp3");

    return true;
}

void GamePlay::GameStart()
{
    // hide "Touch to Play" label
    labelStart->setVisible(false);

    // wake up the player
    player->SetAwake();

    // start moving obstacles
    if (!isObstacleStarted) {
        isObstacleStarted = true;
        this->schedule(CC_SCHEDULE_SELECTOR(GamePlay::GameUpdate));
    }

    //scroll cloud
    cloud->Scroll();

    // play the background music
    SoundManager::Instance()->PlayMusic();
}

void GamePlay::GameUpdate(float dt)
{
    background->Update(dt);
    player->Fly(dt);
    StartObstacles(dt);
    score += 0.25f * dt;
    AddScore();

    // ----------
    // DIFFICULTY
    // ----------
    // let the user cheer up for a while
    if (score >= 5) {
        if (!(enemyEasy->IsScrolling())) {
            enemyEasy->Scroll();
            enemyEasy->setScrolling(true);
        }
    }
    // build up tension
    if (score >= 15) {
        if (!(enemyNormal->IsScrolling())) {
            enemyNormal->Scroll();
            enemyNormal->setScrolling(true);
        }
    }
    // make the user sad
    if (score >= 25) {
        if (!(enemyHard->IsScrolling())) {
            enemyHard->Scroll();
            enemyHard->setScrolling(true);
        }
    }
    // make the user lose all hope
    if (score >= 50) {
        if (!(enemyImpossible->IsScrolling())) {
            enemyImpossible->Scroll();
            enemyImpossible->setScrolling(true);
        }
    }
}

void GamePlay::GameOver()
{
    SoundManager::Instance()->PlaySFX("sfx/hit.mp3");
    player->Reset();
    StopObstacles();
    SoundManager::Instance()->StopMusic();
    enemyEasy->sprite->stopAllActions();
    enemyNormal->sprite->stopAllActions();
    enemyHard->sprite->stopAllActions();
    enemyImpossible->sprite->stopAllActions();

    // go to game over scene
    Scene *scene = GameOver::createScene(score);
    Director::getInstance()->replaceScene(TransitionFade::create(TRANSITION_FADEOUT, scene));
}

void GamePlay::AddScore()
{
    auto stringScore = StringUtils::format("Score: %i", (int) (floor(score)));
    labelScore->setString(stringScore.c_str());
}

void GamePlay::StartObstacles(float dt)
{
    int offsetX = 0;

    for (auto obstacle : this->obstacles) {
        if (!obstacle->isActive) {
            if (gameTimeElapsed > gameTime) {
                obstacle->setVisible(true);
                obstacle->isActive = true;

                //offset right of the screen
                if (offsetX <= SCREEN_OFFSET_X_MAX) {
                    offsetX += SCREEN_OFFSET_X_MIN;
                }

                // start obstacles by type
                if (obstacle->type == 0) {
                    int max = obstacle->getContentSize().height / 2 - player->GetHeight() / 2;
                    int offsetY = cocos2d::random(0, max);
                    obstacle->Start(offsetX, offsetY);
                    obstacle->isActive = true;
                }

                if (obstacle->type == 1) {    // 1/4 - 1/2 of screen width
                    int max = obstacle->getContentSize().height / 2 + player->GetHeight() / 2;
                    int offsetY = cocos2d::random(0, max);
                    obstacle->Start(offsetX, offsetY);
                    obstacle->isActive = true;
                }

                gameTime += dt + gameTimeDefault;
            }
            else {
                gameTimeElapsed += dt;
            }
        }
    }
}

void GamePlay::StopObstacles()
{
    for (auto obstacle : this->obstacles) {
        obstacle->Stop();
    }
}

void GamePlay::CreateObstacles()
{
    // create obstacle pool
    obstacles = Vector<Obstacle *>{OBSTACLE_POOL_SIZE};

    // create obstacles
    for (int i = 0; i < OBSTACLE_POOL_SIZE / OBSTACLE_TYPES_SIZE; i++) {
        AddObstacle("rock_up.png", OBSTACLE_TYPE_UP);
        AddObstacle("rock_down.png", OBSTACLE_TYPE_DOWN);
    }
}

void GamePlay::AddObstacle(std::string texture, int type)
{
    // create obstacle
    Obstacle *obstacle = new Obstacle;
    obstacle->playerHeight = this->playerHeight;
    obstacle->setTexture(texture);
    obstacle->type = type;
    obstacle->setFlippedX(false);                       //voodoo cocos (wtf)...
    obstacle->setFlippedY(false);                       //...is flipped by default
    obstacle->setAnchorPoint(Vec2(0.5f, 0.5f)); //...is not anchored at 0.5f by default
    obstacle->setVisible(false);
    obstacle->isActive = false;
    obstacle->groundHeight = this->groundHeight;    //extra offset Y for down obstacles

    // create obstacle physics body
    obstacle->CreateObstaclePhysicsHelper(texture);
    obstacle->setPosition(-2 * obstacle->getContentSize().width, 0); //set body way off the screen

    // add to scene
    this->addChild(obstacle, LAYER_OBSTACLE);

    // add to pool
    obstacles.pushBack(obstacle);
}
