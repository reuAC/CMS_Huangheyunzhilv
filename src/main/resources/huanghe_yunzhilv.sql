SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article_tags_map
-- ----------------------------
DROP TABLE IF EXISTS `article_tags_map`;
CREATE TABLE `article_tags_map`  (
  `article_id` int NOT NULL,
  `tag_id` int NOT NULL,
  PRIMARY KEY (`article_id`, `tag_id`) USING BTREE,
  INDEX `tag_id`(`tag_id` ASC) USING BTREE,
  CONSTRAINT `article_tags_map_ibfk_1` FOREIGN KEY (`article_id`) REFERENCES `articles` (`article_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `article_tags_map_ibfk_2` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`tag_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of article_tags_map
-- ----------------------------
INSERT INTO `article_tags_map` VALUES (4, 3);
INSERT INTO `article_tags_map` VALUES (1, 4);
INSERT INTO `article_tags_map` VALUES (33, 4);
INSERT INTO `article_tags_map` VALUES (34, 4);
INSERT INTO `article_tags_map` VALUES (35, 4);
INSERT INTO `article_tags_map` VALUES (36, 4);
INSERT INTO `article_tags_map` VALUES (28, 7);
INSERT INTO `article_tags_map` VALUES (20, 9);
INSERT INTO `article_tags_map` VALUES (14, 10);
INSERT INTO `article_tags_map` VALUES (32, 12);
INSERT INTO `article_tags_map` VALUES (1, 13);
INSERT INTO `article_tags_map` VALUES (4, 14);

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles`  (
  `article_id` int NOT NULL AUTO_INCREMENT,
  `article_slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章URL友好标识符',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章主标题',
  `author` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作者',
  `publish_date` date NULL DEFAULT NULL COMMENT '发布日期',
  `estimated_read_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预计阅读时间',
  `featured_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '特色图片路径',
  `lead_paragraph` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '引导段落/摘要',
  `content_html` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '文章主体HTML',
  `page_title_seo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文章页SEO标题',
  `status` enum('published','draft') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'draft' COMMENT '文章状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`) USING BTREE,
  UNIQUE INDEX `article_slug`(`article_slug` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES (1, 'index-service-culture', '深度文化探索之旅', '黄河云小编', '2025-05-12', '6 分钟', '', '深入黄河文明的腹地，探索千年历史的厚重与灿烂。', '<p>这是一篇关于深度文化探索的文章内容....</p><p>您可以了解黄河流域的古代文明，如仰韶文化、龙山文化等。</p>', '深度文化探索 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-14 13:18:11');
INSERT INTO `articles` VALUES (2, 'index-service-guides', '专业向导的贴心陪伴', '黄河云小编', '2025-05-12', '5 分钟', NULL, '我们的专业向导将为您解读黄河的每一个故事，让您的旅程充满温度。', '<p>这是一篇关于专业向导服务的文章内容...</p><p>向导们熟悉当地风土人情，能提供更个性化的讲解。</p>', '专业向导服务 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (3, 'index-service-safety', '安全舒适的旅程保障', '黄河云小编', '2025-05-12', '5 分钟', NULL, '从交通到住宿，我们为您提供全方位的安全与舒适保障。', '<p>这是一篇关于安全舒适保障的文章内容...</p><p>我们精选合作伙伴，确保您的旅途无忧。</p>', '旅程安全保障 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (4, 'index-sights-mountains', '雄伟山脉的呼唤', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄河沿岸的山脉，不仅有险峻的自然风光，更承载着丰富的地质历史。', '<p>文章内容：探索黄河沿岸的太行山、秦岭等雄伟山脉...</p>', '黄河山脉探索 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (5, 'index-sights-lakes', '宁静湖泊的秘境探幽', '黄河云小编', '2025-05-12', '5 分钟', NULL, '在黄河的滋养下，形成了众多美丽的湖泊，它们是自然的瑰宝。', '<p>文章内容：介绍黄河源头的鄂陵湖、扎陵湖等...</p>', '黄河湖泊秘境 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (6, 'index-sights-cityscape', '黄河岸边都市夜未眠', '黄河云小编', '2025-05-12', '5 分钟', NULL, '沿黄城市在夜晚展现出不同于白日的魅力，灯火辉煌，流光溢彩。', '<p>文章内容：欣赏兰州、郑州、济南等沿黄城市的夜景...</p>', '黄河都市夜景 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (7, 'index-sights-kyoto-maple', '京都红叶般的黄河秋色', '黄河云小编', '2025-05-12', '5 分钟', NULL, '秋季的黄河两岸，层林尽染，景色如画，不输京都的红叶。', '<p>文章内容：黄河秋季风光介绍...</p>', '黄河秋色 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (8, 'index-sights-maldives-waters', '媲美马尔代夫的黄河湿地', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄河拥有广阔的湿地资源，水清沙白，候鸟天堂。', '<p>文章内容：介绍黄河三角洲等湿地...</p>', '黄河湿地风光 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (9, 'index-sights-swiss-snow', '黄河雪山的圣洁与壮美', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄河上游的雪山，如同瑞士的雪峰般纯洁壮丽。', '<p>文章内容：昆仑山、巴颜喀拉山雪景...</p>', '黄河雪山 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (10, 'index-sights-paris-tower', '黄河铁桥的百年风情', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄河上的铁桥，是工程的奇迹，也是历史的见证。', '<p>文章内容：兰州中山桥等黄河铁桥介绍...</p>', '黄河铁桥 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (11, 'index-sights-egypt-pyramids', '黄土高原的文明奇迹', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄土高原孕育了独特的窑洞文化和农耕文明，是中华文明的摇篮。', '<p>文章内容：黄土高原文化探索...</p>', '黄土高原文明 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (12, 'index-sights-iceland-aurora', '黄河星空的璀璨传说', '黄河云小编', '2025-05-12', '5 分钟', NULL, '在黄河上游的纯净之地，可以仰望到媲美极光的璀璨星空。', '<p>文章内容：黄河源头星空观测...</p>', '黄河星空 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (13, 'index-sights-rome-ruins', '黄河古城的千年遗韵', '黄河云小编', '2025-05-12', '5 分钟', NULL, '黄河沿岸散落着众多古城遗址，如同罗马古迹般诉说着历史。', '<p>文章内容：探访汉长安城、洛阳故城等...</p>', '黄河古城 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (14, 'jingpin-family', '黄河亲子度假全攻略', '黄河云小编', '2025-05-12', '5 分钟', NULL, '带上家人，一起在黄河边留下难忘的欢乐时光。', '<p>文章内容：适合家庭出游的黄河景点和活动...</p>', '黄河亲子游 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (15, 'jingpin-safari', '黄河生态野趣之旅', '黄河云小编', '2025-05-12', '5 分钟', NULL, '深入黄河自然保护区，邂逅珍稀野生动植物。', '<p>文章内容：黄河湿地、鸟类观测等生态旅游介绍...</p>', '黄河生态游 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (16, 'jingpin-honeymoon', '黄河浪漫蜜月定制', '黄河云小编', '2025-05-12', '5 分钟', NULL, '在母亲河的见证下，开启你们的甜蜜新篇章。', '<p>文章内容：黄河沿岸适合蜜月的浪漫去处和体验...</p>', '黄河蜜月游 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (17, 'jingpin-beach', '黄河边的“海滩”假日', '黄河云小编', '2025-05-12', '5 分钟', NULL, '虽然黄河不靠海，但其广阔的沙洲和湖泊也能带来独特的“海滩”体验。', '<p>文章内容：黄河边的沙滩、湖泊度假...</p>', '黄河沙滩假日 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (18, 'jingpin-cultural', '黄河深度文化体验游', '黄河云小编', '2025-05-12', '5 分钟', NULL, '不仅仅是观光，更是沉浸式的文化体验。', '<p>文章内容：参与非遗传承、民俗活动等...</p>', '黄河文化体验 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (19, 'jingpin-cruise', '黄河豪华游轮慢生活', '黄河云小编', '2025-05-12', '5 分钟', NULL, '在黄河的游轮上，享受一段悠闲惬意的慢时光。', '<p>文章内容：黄河游轮航线和体验介绍...</p>', '黄河游轮 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (20, 'zijiayou-coastal', '黄河沿岸景观大道自驾攻略', '黄河云小编', '2025-05-12', '5 分钟', NULL, '驾车行驶在黄河大堤或沿河公路上，感受母亲河的壮丽与温柔。', '<p>文章内容：推荐的黄河沿岸自驾路线...</p>', '黄河沿岸自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (21, 'zijiayou-mountain', '挑战黄河山脉自驾之旅', '黄河云小编', '2025-05-12', '5 分钟', NULL, '征服黄河穿行的山脉，体验驾驶的乐趣与美景的震撼。', '<p>文章内容：穿越太行山、吕梁山等黄河山脉的自驾路线...</p>', '黄河山脉自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (22, 'zijiayou-desert', '黄河边的沙漠自驾探险', '黄河云小编', '2025-05-12', '5 分钟', NULL, '体验黄河与沙漠交汇的奇特景观，感受西北的苍凉与壮阔。', '<p>文章内容：腾格里沙漠、库布齐沙漠边缘的黄河自驾...</p>', '黄河沙漠自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (23, 'zijiayou-forest', '黄河森林秘境自驾穿行', '黄河云小编', '2025-05-12', '5 分钟', NULL, '在黄河上中游的森林区域，享受清新的空气和宁静的驾驶体验。', '<p>文章内容：黄河沿岸森林公园自驾...</p>', '黄河森林自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (24, 'zijiayou-historic', '黄河历史古道自驾寻踪', '黄河云小编', '2025-05-12', '5 分钟', NULL, '沿着古老的驿道和丝绸之路的黄河段，追寻历史的足迹。', '<p>文章内容：沿着黄河寻找古战场、古关隘...</p>', '黄河古道自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (25, 'zijiayou-lakeside', '黄河湖泊环线自驾游', '黄河云小编', '2025-05-12', '5 分钟', NULL, '围绕黄河形成的湖泊群进行自驾，享受湖光山色。', '<p>文章内容：青海湖（虽非黄河直接形成但相近）、黄河源湖群自驾...</p>', '黄河湖泊自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (26, 'zijiayou-nationalpark', '黄河国家公园自驾全览', '黄河云小编', '2025-05-12', '5 分钟', NULL, '探索黄河沿线的国家级地质公园、森林公园和湿地公园。', '<p>文章内容：黄河国家地质公园等自驾介绍...</p>', '黄河国家公园自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (27, 'zijiayou-vineyard', '黄河岸边的葡萄酒庄自驾品鉴', '黄河云小编', '2025-05-12', '5 分钟', NULL, '宁夏贺兰山东麓等黄河灌溉区，是中国重要的葡萄酒产区，体验酒庄文化。', '<p>文章内容：贺兰山东麓酒庄自驾...</p>', '黄河酒庄自驾 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (28, 'huanghe-shici-zhanglang', '黄河诗词长廊深度游', '黄河云小编', '2025-05-12', '8 分钟', NULL, '探寻李白杜甫笔下的黄河雄姿，感受千年诗词的魅力。', '<p>这是一篇关于黄河诗词长廊的文章，包含著名诗篇和相关景点...</p>', '黄河诗词长廊 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (29, 'hukou-shihun', '壶口瀑布的诗魂与呐喊', '黄河云小编', '2025-05-12', '6 分钟', NULL, '亲临壶口，感受“黄河之水天上来”的磅礴气势与诗词意境。', '<p>文章内容：壶口瀑布的壮丽景色与相关诗词解读...</p>', '壶口瀑布诗魂 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (30, 'hetao-wenhua', '河套文化的诗意探源', '黄河云小编', '2025-05-12', '7 分钟', NULL, '追溯黄河“几”字弯中孕育的河套文化，寻找中华文明的诗意起点。', '<p>文章内容：河套地区的历史文化与诗词遗存...</p>', '河套文化探源 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (31, 'dayu-zhishui', '大禹治水传说地的诗与史', '黄河云小编', '2025-05-12', '9 分钟', NULL, '寻访与大禹治水相关的黄河古迹，探索神话传说与历史诗篇。', '<p>文章内容：龙门、孟津等大禹治水相关地点的传说与诗词...</p>', '大禹治水传说地 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (32, 'hongse-yanan-baotashan', '延安宝塔山：革命的灯塔', '黄河云小编', '2025-05-12', '7 分钟', NULL, '延安宝塔山是中国革命圣地延安的标志性建筑，见证了中国共产党领导下的波澜壮阔的革命历程。', '<p>文章详细介绍延安宝塔山的历史背景、建筑特色及其在中国革命史上的重要地位...</p><p>宝塔山，古称嘉岭山，位于延安城东南，宝塔建于唐代，现为明代建筑。它不仅是历史的见证，也是延安精神的象征。</p>', '延安宝塔山 - 黄河云之旅', 'published', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `articles` VALUES (33, 'about-us', '关于我们 - 黄河云之旅', '网站管理员', '2023-01-01', '3 分钟', NULL, '欢迎来到黄河云之旅！我们致力于分享黄河的壮丽风光、深厚文化和精彩故事。', '<p>黄河云之旅是一个专注于黄河文化与旅游的在线平台。我们的使命是：</p><ul><li><strong>探索与发现：</strong> 深入挖掘黄河沿岸的自然奇观、历史遗迹和民俗风情。</li><li><strong>分享与传播：</strong> 通过高质量的文章、图片和互动体验，向世界展示黄河的魅力。</li><li><strong>连接与服务：</strong> 为游客提供实用的旅游资讯，为文化爱好者搭建交流的桥梁。</li></ul><p>我们的团队由一群热爱黄河、熟悉旅游、精通互联网技术的专业人士组成。我们相信，通过我们的努力，能让更多人了解黄河、走进黄河、爱上黄河。</p><p>感谢您的访问，期待与您一同开启精彩的黄河云之旅！</p>', '关于我们 - 黄河云之旅', 'published', '2025-05-14 13:40:02', '2025-05-14 13:40:02');
INSERT INTO `articles` VALUES (34, 'contact-information', '联系我们 - 获取帮助与支持', '网站管理员', '2023-01-01', '3 分钟', NULL, '如果您有任何问题、建议或合作意向，请随时通过以下方式与我们联系。', '<h3>联系方式：</h3><p>我们非常乐意听取您的声音。您可以通过以下渠道联系黄河云之旅团队：</p><ul><li><strong>电子邮件：</strong> <a href=\"mailto:service@huanghe-yunzhilv.com\">service@huanghe-yunzhilv.com</a></li><li><strong>服务热线：</strong> 400-000-0000 (工作日 9:00 - 18:00)</li><li><strong>在线反馈：</strong> 请访问我们网站的“意见反馈”页面提交您的宝贵意见。</li></ul><h3>商务合作：</h3><p>如果您代表媒体、旅游机构或其他商业伙伴，并希望与黄河云之旅进行合作，请联系：</p><ul><li><strong>合作邮箱：</strong> <a href=\"mailto:partner@huanghe-yunzhilv.com\">partner@huanghe-yunzhilv.com</a></li></ul><p>我们期待您的来信！</p>', '联系方式 - 黄河云之旅', 'published', '2025-05-14 13:40:02', '2025-05-14 13:40:02');
INSERT INTO `articles` VALUES (35, 'privacy-policy', '隐私政策 - 保护您的个人信息', '网站管理员', '2023-01-01', '3 分钟', NULL, '黄河云之旅高度重视用户的隐私权，本隐私政策规定了我们如何收集、使用、披露、处理和保护您在使用我们服务时提供给我们的个人信息。', '<h4>1. 我们收集哪些信息</h4><p>当您注册账户、浏览内容、参与活动或联系我们时，我们可能会收集您的姓名、电子邮件地址、联系电话等个人身份信息，以及您的设备信息、浏览记录等非个人身份信息。</p><h4>2. 我们如何使用您的信息</h4><p>我们收集的信息将用于：</p><ul><li>提供、维护和改进我们的服务；</li><li>与您沟通，回应您的查询和请求；</li><li>向您发送相关的服务通知、促销信息（在您同意的情况下）；</li><li>进行数据分析和研究，以提升用户体验。</li></ul><h4>3. 信息安全</h4><p>我们采取了行业标准的安全措施来保护您的个人信息，防止未经授权的访问、披露、使用、修改、损坏或丢失。</p><h4>4. 您的权利</h4><p>您有权访问、更正、删除您的个人信息，以及撤回您的授权同意。具体操作请联系我们。</p><h4>5. 政策更新</h4><p>我们可能会不时更新本隐私政策。任何重大变更，我们将通过网站公告或邮件通知您。</p><p><em>最后更新日期：2023年1月1日</em></p>', '隐私政策 - 黄河云之旅', 'published', '2025-05-14 13:40:02', '2025-05-14 13:40:02');
INSERT INTO `articles` VALUES (36, 'terms-of-service', '服务条款 - 使用黄河云之旅的规则', '网站管理员', '2023-01-01', '3 分钟', NULL, '欢迎使用黄河云之旅！这些服务条款适用于您对本网站及其提供的所有服务的访问和使用。', '<h4>1. 接受条款</h4><p>通过访问或使用本网站，即表示您同意受本服务条款的约束。如果您不同意所有条款，则不得访问或使用本服务。</p><h4>2. 服务内容</h4><p>黄河云之旅提供黄河相关的文化旅游资讯、文章、图片等内容。我们保留随时修改或终止服务的权利，恕不另行通知。</p><h4>3. 用户行为</h4><p>您同意不将本服务用于任何非法或本条款禁止的目的。您不得：</p><ul><li>发布任何诽谤、淫秽、威胁、侵犯他人隐私或知识产权的内容；</li><li>进行任何可能损害、禁用、过载或损害本服务的行为；</li><li>试图未经授权访问本服务的任何部分、其他帐户、计算机系统或连接到本服务的网络。</li></ul><h4>4. 知识产权</h4><p>本网站及其原始内容、特性和功能由黄河云之旅及其许可方拥有，并受版权、商标和其他知识产权法律的保护。</p><h4>5. 免责声明</h4><p>本服务按“现状”和“可用”的基础提供。我们不保证服务将不间断、及时、安全或无错误。</p><h4>6. 条款变更</h4><p>我们保留随时修改这些服务条款的权利。修改后的条款将在网站上公布后立即生效。</p><p><em>最后更新日期：2023年1月1日</em></p>', '服务条款 - 黄河云之旅', 'published', '2025-05-14 13:40:02', '2025-05-14 13:40:02');

-- ----------------------------
-- Table structure for cards
-- ----------------------------
DROP TABLE IF EXISTS `cards`;
CREATE TABLE `cards`  (
  `card_id` int NOT NULL AUTO_INCREMENT,
  `section_id` int NULL DEFAULT NULL COMMENT '所属版块ID (可选)',
  `page_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属页面代码 (如果非版块特定)',
  `card_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡片类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡片标题',
  `subtitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '卡片副标题或描述',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片路径',
  `image_alt_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片ALT文本',
  `placeholder_logo_text` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '占位符Logo文字',
  `link_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '传统链接URL (JS点击处理前的备用)',
  `link_text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '链接文本',
  `linked_article_slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联文章的slug，点击卡片跳转',
  `meta_info_1` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '额外元信息1',
  `meta_info_2` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '额外元信息2',
  `icon_svg` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图标SVG代码 (可选, 用于服务特色等)',
  `display_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`card_id`) USING BTREE,
  INDEX `section_id`(`section_id` ASC) USING BTREE,
  INDEX `page_code`(`page_code` ASC) USING BTREE,
  INDEX `linked_article_slug`(`linked_article_slug` ASC) USING BTREE,
  CONSTRAINT `cards_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `content_sections` (`section_id`) ON DELETE SET NULL ON UPDATE RESTRICT,
  CONSTRAINT `cards_ibfk_2` FOREIGN KEY (`page_code`) REFERENCES `pages` (`page_code`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `cards_ibfk_3` FOREIGN KEY (`linked_article_slug`) REFERENCES `articles` (`article_slug`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 32 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '卡片信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cards
-- ----------------------------
INSERT INTO `cards` VALUES (1, 2, 'index', 'service_feature', '深度文化探索', '精心设计的路线...', NULL, NULL, NULL, 'culture-tours', '了解更多 →', 'index-service-culture', NULL, NULL, '<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-map-pin\"><path d=\"M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z\"></path><circle cx=\"12\" cy=\"10\" r=\"3\"></circle></svg>', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `cards` VALUES (2, 2, 'index', 'service_feature', '专业向导陪同', '经验丰富的本地向导...', NULL, NULL, NULL, 'guides', '了解更多 →', 'index-service-guides', NULL, NULL, '<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-users\"><path d=\"M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2\"></path><circle cx=\"9\" cy=\"7\" r=\"4\"></circle><path d=\"M23 21v-2a4 4 0 0 0-3-3.87\"></path><path d=\"M16 3.13a4 4 0 0 1 0 7.75\"></path></svg>', 2, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `cards` VALUES (3, 2, 'index', 'service_feature', '安全舒适保障', '严格筛选合作方...', NULL, NULL, NULL, 'safety', '了解更多 →', 'index-service-safety', NULL, NULL, '<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"48\" height=\"48\" viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\" class=\"feather feather-shield\"><path d=\"M12 22s8-4 8-10V5l-8-3-8 3v7c0 6 8 10 8 10z\"></path></svg>', 3, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `cards` VALUES (4, 1, 'index', 'index_special_sight', '兰州白塔山', '一次难忘的登山之旅', 'images/兰州白塔山.png', '兰州白塔山', '', '', '', 'index-sights-mountains', 'featured_large', '', '', 1, '2025-05-12 06:04:20', '2025-05-14 14:04:21');
INSERT INTO `cards` VALUES (5, 1, 'index', 'index_special_sight', '滨海度假胜地', '享受大自然的馈赠', 'images/龙口滨海旅游度假区.png', '美好度假区', '', '', '', 'index-sights-lakes', 'featured_large', '', '', 2, '2025-05-12 06:04:20', '2025-05-14 14:05:15');
INSERT INTO `cards` VALUES (6, 1, 'index', 'index_special_sight', '内蒙古博物馆', '感受历史底蕴，畅想过去', 'images/内蒙古博物馆.png', '内蒙古博物馆', '', '', '', 'index-sights-cityscape', 'featured_large', '', '', 3, '2025-05-12 06:04:20', '2025-05-14 14:06:18');
INSERT INTO `cards` VALUES (7, 1, 'index', 'index_special_sight', '乌梁素海', '', 'images/内蒙古乌梁素海.png', '乌梁素海', '', '', '', 'index-sights-kyoto-maple', 'standard_small', '', '', 4, '2025-05-12 06:04:20', '2025-05-14 14:06:50');
INSERT INTO `cards` VALUES (8, 1, 'index', 'index_special_sight', '青铜峡', '', 'images/宁夏青铜峡景区.png', '青铜峡', '', '', '', 'index-sights-maldives-waters', 'standard_small', '', '', 5, '2025-05-12 06:04:20', '2025-05-14 14:07:09');
INSERT INTO `cards` VALUES (9, 1, 'index', 'index_special_sight', '齐文化博物馆', '', 'images/齐文化博物馆.png', '齐文化博物馆', '', '', '', 'index-sights-swiss-snow', 'standard_small', '', '', 6, '2025-05-12 06:04:20', '2025-05-14 14:07:25');
INSERT INTO `cards` VALUES (10, 1, 'index', 'index_special_sight', '青海湖', '', 'images/青海湖.png', '青海湖', '', '', '', 'index-sights-paris-tower', 'standard_small', '', '', 7, '2025-05-12 06:04:20', '2025-05-14 14:07:38');
INSERT INTO `cards` VALUES (11, 1, 'index', 'index_special_sight', '太行大峡谷', '', 'images/太行大峡谷.png', '太行大峡谷', '', '', '', 'index-sights-egypt-pyramids', 'standard_small', '', '', 8, '2025-05-12 06:04:20', '2025-05-14 14:07:58');
INSERT INTO `cards` VALUES (12, 1, 'index', 'index_special_sight', '泰山', '', 'images/泰山.png', '泰山', '', '', '', 'index-sights-iceland-aurora', 'standard_small', '', '', 9, '2025-05-12 06:04:20', '2025-05-14 14:08:16');
INSERT INTO `cards` VALUES (13, 1, 'index', 'index_special_sight', '雁门关', '', 'images/雁门关.png', '雁门关', '', '', '', 'index-sights-rome-ruins', 'standard_small', '', '', 10, '2025-05-12 06:04:20', '2025-05-14 14:08:34');
INSERT INTO `cards` VALUES (14, NULL, 'jingpinyou', 'idea_placeholder', '河南登封少林寺', '', 'images/河南登封少林寺.png', '', '', '', '', 'jingpin-family', '', '', '', 1, '2025-05-12 06:04:20', '2025-05-14 14:11:21');
INSERT INTO `cards` VALUES (15, NULL, 'jingpinyou', 'idea_placeholder', '河南红石峡', '', 'images/河南红石峡.png', '', '', '', '', 'jingpin-safari', '', '', '', 2, '2025-05-12 06:04:20', '2025-05-14 14:12:10');
INSERT INTO `cards` VALUES (16, NULL, 'jingpinyou', 'idea_placeholder', '河南郑州黄河风景名胜区', '', 'images/河南郑州黄河风景名胜区.png', '', '', '', '', 'jingpin-honeymoon', '', '', '', 3, '2025-05-12 06:04:20', '2025-05-14 14:12:38');
INSERT INTO `cards` VALUES (17, NULL, 'jingpinyou', 'idea_placeholder', '黄河壶口瀑布', '', 'images/黄河壶口瀑布.png', '', '', '', '', 'jingpin-beach', '', '', '', 4, '2025-05-12 06:04:20', '2025-05-14 14:13:07');
INSERT INTO `cards` VALUES (18, NULL, 'jingpinyou', 'idea_placeholder', '黄河口生态旅游区', '', 'images/黄河口生态旅游区.png', '', '', '', '', 'jingpin-cultural', '', '', '', 5, '2025-05-12 06:04:20', '2025-05-14 14:13:35');
INSERT INTO `cards` VALUES (19, NULL, 'jingpinyou', 'idea_placeholder', '云冈石窟', '', 'images/云冈石窟.png', '', '', '', '', 'jingpin-cruise', '', '', '', 6, '2025-05-12 06:04:20', '2025-05-14 14:13:49');
INSERT INTO `cards` VALUES (20, NULL, 'zijiayou', 'roadtrip_route', '宝鸡法门寺', '', 'images/宝鸡法门寺.png', '', '', '', '', 'zijiayou-coastal', '', '', '', 1, '2025-05-12 06:04:20', '2025-05-14 14:16:36');
INSERT INTO `cards` VALUES (21, NULL, 'zijiayou', 'roadtrip_route', '大窑遗址', '', 'images/大窑遗址.png', '', '', '', '', 'zijiayou-mountain', '', '', '', 2, '2025-05-12 06:04:20', '2025-05-14 14:16:52');
INSERT INTO `cards` VALUES (22, NULL, 'zijiayou', 'roadtrip_route', '东明黄河国家湿地公园', '', 'images/东明黄河国家湿地公园.png', '', '', '', '', 'zijiayou-desert', '', '', '', 3, '2025-05-12 06:04:20', '2025-05-14 14:17:12');
INSERT INTO `cards` VALUES (23, NULL, 'zijiayou', 'roadtrip_route', '甘肃黄河石林', '', 'images/甘肃黄河石林.png', '', '', '', '', 'zijiayou-forest', '', '', '', 4, '2025-05-12 06:04:20', '2025-05-14 14:17:29');
INSERT INTO `cards` VALUES (24, NULL, 'zijiayou', 'roadtrip_route', '黄龙溪风景区', '', 'images/黄龙溪风景区.png', '', '', '', '', 'zijiayou-historic', '', '', '', 5, '2025-05-12 06:04:20', '2025-05-14 14:17:47');
INSERT INTO `cards` VALUES (25, NULL, 'zijiayou', 'roadtrip_route', '开封封龙亭公园', '', 'images/开封封龙亭公园.png', '', '', '', '', 'zijiayou-lakeside', '', '', '', 6, '2025-05-12 06:04:20', '2025-05-14 14:18:11');
INSERT INTO `cards` VALUES (26, NULL, 'zijiayou', 'roadtrip_route', '曲阜三孔景区', '', 'images/曲阜三孔景区.png', '', '', '', '', 'zijiayou-nationalpark', '', '', '', 7, '2025-05-12 06:04:20', '2025-05-14 14:18:23');
INSERT INTO `cards` VALUES (27, NULL, 'zijiayou', 'roadtrip_route', '山东泰山', '', 'images/山东泰山.png', '', '', '', '', 'zijiayou-vineyard', '', '', '', 8, '2025-05-12 06:04:20', '2025-05-14 14:19:02');
INSERT INTO `cards` VALUES (28, NULL, 'shiciyou', 'guide_detailed', '黄河诗词长廊', '探寻李白杜甫笔下的黄河雄姿。', 'images/shiciyou_guide1.jpg', '黄河诗词之旅', NULL, NULL, '阅读指南', 'huanghe-shici-zhanglang', '8分钟阅读', NULL, NULL, 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `cards` VALUES (29, NULL, 'shiciyou', 'guide_detailed', '壶口瀑布诗魂', '感受“黄河之水天上来”的磅礴气势。', 'images/shiciyou_guide2.jpg', '壶口瀑布', '', '', '阅读指南', 'hukou-shihun', '6分钟阅读', '', '', 2, '2025-05-12 06:04:20', '2025-05-14 14:20:51');
INSERT INTO `cards` VALUES (30, NULL, 'shiciyou', 'guide_detailed', '河套文化探源', '追溯黄河文明的诗意起点。', 'images/shiciyou_guide3.jpg', '河套文化', NULL, NULL, '阅读指南', 'hetao-wenhua', '7分钟阅读', NULL, NULL, 3, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `cards` VALUES (31, NULL, 'shiciyou', 'guide_detailed', '大禹治水传说地', '寻访与黄河相关的神话与史诗。', 'images/shiciyou_guide4.jpg', '大禹治水', NULL, NULL, '阅读指南', 'dayu-zhishui', '9分钟阅读', NULL, NULL, 4, '2025-05-12 06:04:20', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for carousel_slides
-- ----------------------------
DROP TABLE IF EXISTS `carousel_slides`;
CREATE TABLE `carousel_slides`  (
  `slide_id` int NOT NULL AUTO_INCREMENT,
  `page_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属页面代码',
  `image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '幻灯片图片路径',
  `image_alt_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片ALT文本',
  `caption_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图片上标题',
  `caption_text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '图片上描述文字',
  `link_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '点击跳转链接 (可选, 传统链接)',
  `linked_article_slug` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联文章的slug，用于JS点击跳转',
  `display_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `is_active` tinyint(1) NULL DEFAULT 1 COMMENT '是否启用',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`slide_id`) USING BTREE,
  INDEX `page_code`(`page_code` ASC) USING BTREE,
  INDEX `linked_article_slug`(`linked_article_slug` ASC) USING BTREE,
  CONSTRAINT `carousel_slides_ibfk_1` FOREIGN KEY (`page_code`) REFERENCES `pages` (`page_code`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `carousel_slides_ibfk_2` FOREIGN KEY (`linked_article_slug`) REFERENCES `articles` (`article_slug`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '轮播图幻灯片表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of carousel_slides
-- ----------------------------
INSERT INTO `carousel_slides` VALUES (1, 'hongseyou', 'images/carousel_red1_baotashan.jpg', '红色景点图1：延安宝塔山', '延安宝塔山', '革命圣地延安的象征，见证了中国革命的光辉历程。', NULL, 'hongse-yanan-baotashan', 1, 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `carousel_slides` VALUES (2, 'hongseyou', 'images/carousel_red2_jinggangshan.jpg', '红色景点图2：井冈山', '井冈山', '中国第一个农村革命根据地，\"星星之火，可以燎原\"。', NULL, NULL, 2, 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `carousel_slides` VALUES (3, 'hongseyou', 'images/carousel_red3_hongchuan.jpg', '红色景点图3：南湖红船', '嘉兴南湖红船', '中国共产党第一次全国代表大会在此闭幕，宣告党的诞生。', NULL, NULL, 3, 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for content_sections
-- ----------------------------
DROP TABLE IF EXISTS `content_sections`;
CREATE TABLE `content_sections`  (
  `section_id` int NOT NULL AUTO_INCREMENT,
  `page_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '所属页面代码',
  `section_identifier` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '版块唯一标识',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '版块主标题',
  `subtitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '版块副标题或描述',
  `display_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`section_id`) USING BTREE,
  UNIQUE INDEX `uk_page_section`(`page_code` ASC, `section_identifier` ASC) USING BTREE,
  CONSTRAINT `content_sections_ibfk_1` FOREIGN KEY (`page_code`) REFERENCES `pages` (`page_code`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '内容版块表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of content_sections
-- ----------------------------
INSERT INTO `content_sections` VALUES (1, 'index', 'index_special_sights', '特别景色', '大河之美，一览无余。', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `content_sections` VALUES (2, 'index', 'index_service_features', '我们的服务特色', '为您打造非凡的黄河之旅。', 2, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `content_sections` VALUES (3, 'hongseyou', 'hongseyou_carousel_header', '红色记忆长廊', '铭记历史，砥砺前行。', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `content_sections` VALUES (4, 'jingpinyou', 'jingpinyou_trip_ideas', '主题畅想', '定制您的专属黄河印象。', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `content_sections` VALUES (5, 'zijiayou', 'zijiayou_roadtrip_planner', '黄河自驾路书', '自由驰骋，探索母亲河的脉搏。', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `content_sections` VALUES (6, 'shiciyou', 'shiciyou_expert_guides', '黄河诗路指南', '在诗词中遇见黄河的灵魂。', 1, '2025-05-12 06:04:20', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for global_settings
-- ----------------------------
DROP TABLE IF EXISTS `global_settings`;
CREATE TABLE `global_settings`  (
  `setting_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `setting_value` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '设置项描述',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`setting_key`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '全局设置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of global_settings
-- ----------------------------
INSERT INTO `global_settings` VALUES ('footerCopyrightText', '© 2024 黄河云之旅. 版权所有.', '页脚版权文本', '2025-05-12 06:04:20');
INSERT INTO `global_settings` VALUES ('headerLogoText', '黄河之声', '顶部导航栏Logo文字', '2025-05-12 06:04:20');
INSERT INTO `global_settings` VALUES ('navSearchPlaceholder', '搜索黄河的记忆...', '导航栏搜索框提示文字', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for navigation_items
-- ----------------------------
DROP TABLE IF EXISTS `navigation_items`;
CREATE TABLE `navigation_items`  (
  `nav_item_id` int NOT NULL AUTO_INCREMENT,
  `nav_area` enum('header_main','header_top_right','footer_main') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '导航区域',
  `text` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '链接显示文本',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '链接URL',
  `target_page_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '指向的页面代码，用于active状态',
  `display_order` int NULL DEFAULT 0 COMMENT '显示顺序',
  `icon_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标CSS类 (可选)',
  `is_button` tinyint(1) NULL DEFAULT 0 COMMENT '是否为按钮样式',
  `button_class` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '按钮CSS类',
  `text_before_button` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '按钮前文本（如欢迎语）',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`nav_item_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '导航链接表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of navigation_items
-- ----------------------------
INSERT INTO `navigation_items` VALUES (1, 'header_main', '首页', 'index', 'index', 1, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (2, 'header_main', '红色游', 'hongseyou', 'hongseyou', 2, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (3, 'header_main', '精品游', 'jingpinyou', 'jingpinyou', 3, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (4, 'header_main', '自驾游', 'zijiayou', 'zijiayou', 4, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (5, 'header_main', '诗词游', 'shiciyou', 'shiciyou', 5, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (6, 'header_top_right', '登录', 'login', 'login', 1, NULL, 1, 'btn-blue', '欢迎您的访问', '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (7, 'footer_main', '关于我们', 'article/about-us', 'article_info', 1, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (8, 'footer_main', '联系方式', 'article/contact-information', 'article_info', 2, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (9, 'footer_main', '隐私政策', 'article/privacy-policy', 'article_info', 3, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');
INSERT INTO `navigation_items` VALUES (10, 'footer_main', '服务条款', 'article/terms-of-service', 'article_info', 4, NULL, 0, NULL, NULL, '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for pages
-- ----------------------------
DROP TABLE IF EXISTS `pages`;
CREATE TABLE `pages`  (
  `page_id` int NOT NULL AUTO_INCREMENT,
  `page_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '页面代码标识',
  `page_title_seo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '浏览器SEO标题',
  `hero_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Hero区域大标题',
  `hero_subtitle` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT 'Hero区域副标题',
  `hero_search_placeholder` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Hero搜索框提示',
  `hero_image_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Hero背景图片路径',
  `hero_image_alt_text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Hero背景图片ALT文本',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`page_id`) USING BTREE,
  UNIQUE INDEX `page_code`(`page_code` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '页面基础信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pages
-- ----------------------------
INSERT INTO `pages` VALUES (1, 'index', '黄河云之旅 - 首页', '黄河云之旅', '开启你的母亲河探索征程', '搜索黄河景点、文化...', 'images/banner.jpg', '黄河壮丽风光', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (2, 'hongseyou', '红色游 - 黄河云之旅', '踏寻红色足迹', '重温峥嵘岁月，传承红色基因', '搜索红色景点、历史事件...', 'images/banner_hongse.jpg', '红色旅游主题横幅', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (3, 'jingpinyou', '精品游 - 黄河云之旅', '甄选精品之旅', '体验极致黄河风情，品味高端定制服务', '搜索精品路线、主题体验...', 'images/banner_jingpin.jpg', '精品旅游主题横幅', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (4, 'zijiayou', '自驾游 - 黄河云之旅', '探索无界 · 自由驰骋', '驾驭黄河风光，定制您的专属旅程', '搜索自驾路线、沿途风光...', 'images/banner_zijiayou.jpg', '自驾游主题横幅', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (5, 'shiciyou', '诗词游 - 黄河云之旅', '品味诗意旅程', '循着千古名句，感悟黄河诗词文化', '搜索诗词名篇、文化地标...', 'images/banner_shici.jpg', '诗词游主题横幅', '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (6, 'search', '搜索结果 - 黄河云之旅', NULL, NULL, NULL, NULL, NULL, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (7, 'article_default', '文章详情 - 黄河云之旅', NULL, NULL, NULL, NULL, NULL, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (8, 'login', '用户登录 - 黄河云之旅', NULL, NULL, NULL, NULL, NULL, '2025-05-12 06:04:20', '2025-05-12 06:04:20');
INSERT INTO `pages` VALUES (9, 'register', '用户注册 - 黄河云之旅', NULL, NULL, NULL, NULL, NULL, '2025-05-12 06:04:20', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `tag_id` int NOT NULL AUTO_INCREMENT,
  `tag_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签名称',
  `tag_slug` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标签URL友好标识符',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`tag_id`) USING BTREE,
  UNIQUE INDEX `tag_name`(`tag_name` ASC) USING BTREE,
  UNIQUE INDEX `tag_slug`(`tag_slug` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, '黄河源头', 'huanghe-yuantou', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (2, '青藏高原', 'qingzang-gaoyuan', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (3, '自然风光', 'ziran-fengguang', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (4, '文化探索', 'wenhua-tansuo', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (5, '古代文明', 'gudai-wenming', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (6, '考古', 'kaogu', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (7, '诗词', 'shici', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (8, '文学', 'wenxue', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (9, '自驾游', 'zijiayou', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (10, '家庭游', 'jiatingyou', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (11, '生态', 'shengtai', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (12, '红色旅游', 'hongse-lvyou', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (13, '服务特色', 'fuwu-tese', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (14, '名山大川', 'mingshan-dachuan', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (15, '湖泊湿地', 'hupo-shidi', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (16, '城市风情', 'chengshi-fengqing', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (17, '历史遗迹', 'lishi-yiji', '2025-05-12 06:04:20');
INSERT INTO `tags` VALUES (18, '美食', 'meishi', '2025-05-12 06:04:20');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password_hash` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '哈希后的密码',
  `salt` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码盐值',
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '电子邮箱 (可选,但如果提供则唯一)',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `username`(`username` ASC) USING BTREE,
  UNIQUE INDEX `email`(`email` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'test', 'cnWouAusvs++2hDWFQ7voarrkLDnR33SNxyODg93ciQ=', 'YjTByk6/rRnKFMXMvBd6hMRY7SdnbBBSeZGYijjsR5A=', '', '2025-05-12 06:16:31', '2025-05-12 06:16:31');

SET FOREIGN_KEY_CHECKS = 1;
