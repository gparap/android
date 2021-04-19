/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __SCENE_GAMEPLAY_H__
#define __SCENE_GAMEPLAY_H__

#include "cocos2d.h"
#include "Background.h"
#include "Obstacle.h"
#include "Player.h"
#include "Enemy.h"
#include "Cloud.h"

class GamePlay : public cocos2d::Scene
{
public:
	static cocos2d::Scene *createScene();
	virtual bool init();
	cocos2d::Size visibleSize = cocos2d::Director::getInstance()->getVisibleSize();
	cocos2d::Vec2 visibleOrigin = cocos2d::Director::getInstance()->getVisibleOrigin();

	// game
	cocos2d::Label *labelStart;
	bool isGameStarted;
	void GameStart();
	void GameUpdate(float dt);
	void GameOver();
	float gameTime;
	float gameTimeElapsed;
	float gameTimeDefault;	//for next obstacle
	int groundHeight;		//placeholder

	// score
	float score;
	cocos2d::Label *labelScore;
	void AddScore();

	// background
	Background *background;
	Cloud *cloud;

	// obstacles
	bool isObstacleStarted;
	bool onContactBegin(cocos2d::PhysicsContact &contact);

	// player
	int playerHeight;	//placeholder
	Player *player;
	bool onTouchBegan(cocos2d::Touch *touch, cocos2d::Event *event);

	//enemies
	Enemy *enemyEasy,
		  *enemyNormal,
		  *enemyHard,
		  *enemyImpossible;

private:
	cocos2d::Vector<Obstacle *> obstacles;
	void AddObstacle(std::string texture, int type);
	void StartObstacles(float dt);
	void StopObstacles();
	void CreateObstacles();

	// implement the "static create()" method manually
	CREATE_FUNC(GamePlay);
};

#endif // __SCENE_GAMEPLAY_H__
