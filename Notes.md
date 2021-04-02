Coding notes:
-Checking for checks - checkThreat(Square target): store kings loc, and then use a method on board that checks "canMove()" for each opposing piece. use checkThreat in Kings "canMove()" method to check for revealed/induced check

- still need to work on game class, move class, player class

- more rules; checks and revealed checks, promotion, move logic

-when coding moves... for pawns, check if has moved is true; if not true and a jump move, set epvalid true... if has moved true, and epvalid true (from last turn), set epValid false

-WORKING ON UPDATE METHOD SO checks can be evaluated on a copied board. updating jumps, en passant, and castling needs attention