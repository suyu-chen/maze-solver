(ns mazeSolver)

(def init-maze ["###############################\n"
                "#     #       #       #       #\n"
                "# # # # ##### # ##### # ### ###\n"
                "# # #   #       #   # #   #   #\n"
                "# # ### ######### # # ####### #\n"
                "#S# # # #         # #   #     #\n"
                "### # # # ######### ### # ### #\n"
                "#   #   # #     #   #   #   # #\n"
                "# ### ### # # ### ### ##### # #\n"
                "#   # # # # # # #   #   #   # #\n"
                "### # # # ### # ### ### # ### #\n"
                "#   #   # #   #     # # # #   #\n"
                "# ### ### # ######### # # # ###\n"
                "#   # #   #     #   #   # # # #\n"
                "# # ### ### # ### # # ### # # #\n"
                "# #     #   #     #       #  E#\n"
                "###############################\n"])

(def END "E")
(def START "S")
(def WALL "#")
(def VISITED "*")

(def START_ROW 5)
(def START_COL 1)

(defn get-char-in-maze [maze row col]
  (subs (nth maze row) col (+ col 1)))

(defn replace-char-in-maze [maze row col replacement]
  (assoc maze row (str (subs (nth maze row) 0 col) replacement (subs (nth maze row) (+ col 1) (count (nth maze row))))))

(defn return-not-null-in-4 [a b c d]
  (cond
    (not= a nil) a
    (not= b nil) b
    (not= c nil) c
    (not= d nil) d))

(defn solve-maze [maze row col]
  (let [current-char (get-char-in-maze maze row col)]
    (cond
      (= current-char END) maze
      (or (= current-char WALL) (= current-char VISITED)) nil
      :else
      (let [mod-maze (replace-char-in-maze maze row col VISITED)]
        (return-not-null-in-4 (solve-maze mod-maze (+ row 1) col) (solve-maze mod-maze (- row 1) col)
                              (solve-maze mod-maze row (+ col 1)) (solve-maze mod-maze row (- col 1)))))))

(println "\n" (solve-maze init-maze START_ROW START_COL))
