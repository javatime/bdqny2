declare
  v_cur_uname varchar2(20);
  v_cur_upwd varchar2(50);
  ---�����α�
  cursor mycur is 
    select uname,upwd from tbl_users where uname = 'oracle';
begin
   --���α�
   open mycur;
   --�����α�
/*   loop
   fetch mycur into v_cur_uname,v_cur_upwd;
   if(mycur%NOTFOUND) then
      exit;
   end if;
   dbms_output.put_line(v_cur_uname || ',' || v_cur_upwd);
   end loop;*/
   fetch mycur into v_cur_uname,v_cur_upwd;
   while(mycur%FOUND) LOOP
   dbms_output.put_line(v_cur_uname || ',' || v_cur_upwd);
   fetch mycur into v_cur_uname,v_cur_upwd;
   END LOOP;
   
   --�ر��α�
   close mycur;
end;