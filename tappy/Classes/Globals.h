/*********************
 *                   *
 * Created by gparap *
 *                   *
 *********************/

#ifndef __GLOBALS_H__
#define __GLOBALS_H__

#define TRANSITION_DELAY	0.250f
#define TRANSITION_FADEOUT	2.250f
#define SCALE_FACTOR_SCORE	0.175f		// to keep score scale the same in all devices
#define OBSTACLE_SPEED		0.010f
#define OBSTACLE_TYPE_UP	0
#define OBSTACLE_TYPE_DOWN	1
#define OBSTACLE_TYPES_SIZE	2
#define OBSTACLE_POOL_SIZE	16
#define LAYER_PLAYER		10
#define LAYER_SCORE			9
#define LAYER_ENEMY			8
#define LAYER_CLOUD			11
#define LAYER_GROUND		5
#define LAYER_OBSTACLE		3
#define LAYER_BACKGROUND	0
#define BITMASK_PLAYER		0x00000001	// player collision bit mask
#define BITMASK_GROUND		0x00000010	// ground collision bit mask
#define BITMASK_SCREEN		0x00000100	// screen boundaries collision bit mask
#define BITMASK_OBSTACLE	0x00001000	// obstacle collision bit mask
#define BITMASK_ENEMY		0x00010000	// enemy collision bit mask
#define BACKGROUND_SPEED	0.022	    // background scrolling left speed
#define PLAYER_FLY_SPEED	0.3750
#define GRAVITY				-0.9832
#define SCREEN_OFFSET_X_MAX	480 		// maximum offset right of the screen
#define SCREEN_OFFSET_X_MIN	120			// minimum offset right of the screen

/* constant speed for an object, independent of screen sizes */
#define GET_SPEED_RATIO		(cocos2d::Director::getInstance()->getVisibleSize().width / \
							(cocos2d::Director::getInstance()->getVisibleSize().width / 480))

#endif // __GLOBALS_H__
